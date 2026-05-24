package com.t3h.masterdataservice.controller;

import com.t3h.masterdataservice.entity.Category;
import com.t3h.masterdataservice.entity.Color;
import com.t3h.masterdataservice.entity.Size;
import com.t3h.masterdataservice.repository.CategoryRepository;
import com.t3h.masterdataservice.repository.ColorRepository;
import com.t3h.masterdataservice.repository.SizeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MasterDataController {

    private final CategoryRepository categoryRepository;
    private final ColorRepository colorRepository;
    private final SizeRepository sizeRepository;

    // ─── Categories ───────────────────────────────────────────────────────────
    @GetMapping("/api/categories")
    public ResponseEntity<List<Category>> getCategories() {
        return ResponseEntity.ok(categoryRepository.findRootCategories());
    }

    @PostMapping("/api/categories")
    public ResponseEntity<Category> createCategory(@RequestBody Category category) {
        category.setDeleted((byte) 0);
        return ResponseEntity.ok(categoryRepository.save(category));
    }

    // ─── Colors ───────────────────────────────────────────────────────────────
    @GetMapping("/api/colors")
    public ResponseEntity<List<Color>> getColors() {
        return ResponseEntity.ok(colorRepository.findByDeletedOrderByIdAsc((byte) 0));
    }

    @PostMapping("/api/colors")
    public ResponseEntity<Color> createColor(@RequestBody Color color) {
        color.setDeleted((byte) 0);
        return ResponseEntity.ok(colorRepository.save(color));
    }

    // ─── Sizes ────────────────────────────────────────────────────────────────
    @GetMapping("/api/sizes")
    public ResponseEntity<List<Size>> getSizes() {
        return ResponseEntity.ok(sizeRepository.findByDeletedOrderByIdAsc((byte) 0));
    }

    @PostMapping("/api/sizes")
    public ResponseEntity<Size> createSize(@RequestBody Size size) {
        size.setDeleted((byte) 0);
        return ResponseEntity.ok(sizeRepository.save(size));
    }
}
