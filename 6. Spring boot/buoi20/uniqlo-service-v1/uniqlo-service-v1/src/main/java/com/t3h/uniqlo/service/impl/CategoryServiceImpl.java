package com.t3h.uniqlo.service.impl;

import com.t3h.uniqlo.dto.CategoryFormDto;
import com.t3h.uniqlo.dto.CategoryListItemDto;
import com.t3h.uniqlo.dto.CategoryTreeDto;
import com.t3h.uniqlo.entity.Category;
import com.t3h.uniqlo.mapper.CategoryMapper;
import com.t3h.uniqlo.repository.CategoryRepository;
import com.t3h.uniqlo.service.CategoryService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<CategoryListItemDto> getCategories(String keyword, Integer parentId, int page, int size) {
        return categoryRepository.findAll(keyword, parentId, page, size)
                .stream()
                .map(CategoryMapper::toListItemDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public long countCategories(String keyword, Integer parentId) {
        return categoryRepository.countAll(keyword, parentId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CategoryTreeDto> getCategoryTree(String keyword, Integer parentId) {
        // Fetch all categories (assuming < 10000 records, in-memory tree building is extremely fast)
        List<Category> allEntities = categoryRepository.findAll(null, null, 1, 10000);
        
        Map<Integer, CategoryTreeDto> allNodes = new HashMap<>();
        for (Category entity : allEntities) {
            allNodes.put(entity.getId(), CategoryMapper.toTreeDto(entity));
        }

        // Determine which nodes to keep
        Set<Integer> keptIds = new HashSet<>();
        
        boolean hasFilter = (keyword != null && !keyword.isBlank()) || parentId != null;

        if (hasFilter) {
            String kw = keyword != null ? keyword.trim().toLowerCase() : null;
            
            for (CategoryTreeDto node : allNodes.values()) {
                boolean matchKw = kw == null || node.getName().toLowerCase().contains(kw);
                boolean matchParent = parentId == null || Objects.equals(node.getParentId(), parentId);
                
                if (matchKw && matchParent) {
                    // Backtrack to keep all ancestors
                    Integer currId = node.getId();
                    while (currId != null) {
                        keptIds.add(currId);
                        CategoryTreeDto currNode = allNodes.get(currId);
                        currId = (currNode != null) ? currNode.getParentId() : null;
                    }
                }
            }
        } else {
            keptIds.addAll(allNodes.keySet());
        }

        // Build the tree
        List<CategoryTreeDto> roots = new ArrayList<>();
        
        for (CategoryTreeDto node : allNodes.values()) {
            if (!keptIds.contains(node.getId())) continue;
            
            Integer pId = node.getParentId();
            if (pId == null || !keptIds.contains(pId)) {
                roots.add(node);
            } else {
                CategoryTreeDto parentNode = allNodes.get(pId);
                if (parentNode != null) {
                    parentNode.getChildren().add(node);
                }
            }
        }
        
        // Calculate depth and sort
        for (CategoryTreeDto root : roots) {
            calculateDepthAndSort(root, 0);
        }
        
        roots.sort(Comparator.comparing(CategoryTreeDto::getCreatedAt).reversed());
        return roots;
    }
    
    private void calculateDepthAndSort(CategoryTreeDto node, int depth) {
        node.setDepth(depth);
        if (node.getChildren() != null && !node.getChildren().isEmpty()) {
            node.getChildren().sort(Comparator.comparing(CategoryTreeDto::getCreatedAt).reversed());
            for (CategoryTreeDto child : node.getChildren()) {
                calculateDepthAndSort(child, depth + 1);
            }
        }
    }

    @Override
    @Transactional(readOnly = true)
    public CategoryFormDto getCategoryForm(Integer id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Category not found: " + id));
        return CategoryMapper.toFormDto(category);
    }

    @Override
    public void createCategory(CategoryFormDto dto) {
        if (dto.getName() == null || dto.getName().isBlank()) {
            throw new IllegalArgumentException("Category name is required");
        }

        Category category = new Category();
        category.setName(dto.getName());
        category.setDeleted((byte) 0);

        if (dto.getParentId() != null) {
            Category parent = categoryRepository.findById(dto.getParentId())
                    .orElseThrow(() -> new IllegalArgumentException("Parent category not found"));
            category.setParent(parent);
        }

        categoryRepository.save(category);
    }

    @Override
    public void updateCategory(Integer id, CategoryFormDto dto) {
        if (dto.getName() == null || dto.getName().isBlank()) {
            throw new IllegalArgumentException("Category name is required");
        }

        Category existing = categoryRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Category not found: " + id));

        existing.setName(dto.getName());

        if (dto.getParentId() != null) {
            if (dto.getParentId().equals(id)) {
                throw new IllegalArgumentException("Category cannot be its own parent");
            }
            Category parent = categoryRepository.findById(dto.getParentId())
                    .orElseThrow(() -> new IllegalArgumentException("Parent category not found"));
            existing.setParent(parent);
        } else {
            existing.setParent(null);
        }

        categoryRepository.save(existing);
    }

    @Override
    public void deleteCategory(Integer id) {
        int updated = categoryRepository.deleteById(id);
        if (updated == 0) {
            throw new IllegalArgumentException("Category not found: " + id);
        }
    }
}
