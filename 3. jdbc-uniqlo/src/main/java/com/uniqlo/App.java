package com.uniqlo;

import com.uniqlo.model.Product;
import com.uniqlo.model.User;
import com.uniqlo.service.AuthService;
import com.uniqlo.service.ProductService;
import com.uniqlo.service.UserService;
import com.uniqlo.util.ProductSearchCriteria;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

public class App {
    private static final Scanner scanner = new Scanner(System.in);
    private static final AuthService authService = new AuthService();
    private static final UserService userService = new UserService();
    private static final ProductService productService = new ProductService();
    private static User currentUser = null;

    public static void main(String[] args) {
        while (true) {
            if (currentUser == null) {
                showAuthMenu();
            } else {
                showMainMenu();
            }
        }
    }

    private static void showAuthMenu() {
        System.out.println("\n===== UNIQLO CONSOLE =====");
        System.out.println("1. Login");
        System.out.println("2. Register");
        System.out.println("0. Exit");
        System.out.print("Choice: ");
        int choice = Integer.parseInt(scanner.nextLine());

        switch (choice) {
            case 1 -> login();
            case 2 -> register();
            case 0 -> System.exit(0);
        }
    }

    private static void login() {
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();
        currentUser = authService.login(email, password);
        if (currentUser != null) {
            System.out.println("Welcome, " + currentUser.getFullName() + "!");
        } else {
            System.out.println("Login failed!");
        }
    }

    private static void register() {
        System.out.print("Full name: ");
        String name = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();
        User user = User.builder().fullName(name).email(email).build();
        if (authService.register(user, password)) {
            System.out.println("Registration successful!");
        } else {
            System.out.println("Registration failed (email might be taken)!");
        }
    }

    private static void showMainMenu() {
        System.out.println("\n===== MAIN MENU =====");
        System.out.println("1. User Management");
        System.out.println("2. Product Management");
        System.out.println("3. Logout");
        System.out.println("0. Exit");
        System.out.print("Choice: ");
        int choice = Integer.parseInt(scanner.nextLine());

        switch (choice) {
            case 1 -> showUserMenu();
            case 2 -> showProductMenu();
            case 3 -> currentUser = null;
            case 0 -> System.exit(0);
        }
    }

    private static void showUserMenu() {
        System.out.println("\n--- USER MANAGEMENT ---");
        System.out.println("1. List All Users");
        System.out.println("2. Back");
        int choice = Integer.parseInt(scanner.nextLine());
        if (choice == 1) {
            userService.getAllUsers()
                    .forEach(u -> System.out.println(u.getId() + " | " + u.getFullName() + " | " + u.getEmail()));
        }
    }

    private static void showProductMenu() {
        System.out.println("\n--- PRODUCT MANAGEMENT ---");
        System.out.println("1. List Products (with Pagination)");
        System.out.println("2. Smart Search");
        System.out.println("3. Back");
        int choice = Integer.parseInt(scanner.nextLine());

        switch (choice) {
            case 1 -> listProducts(1);
            case 2 -> smartSearch();
        }
    }

    private static void listProducts(int page) {
        ProductSearchCriteria criteria = ProductSearchCriteria.builder()
                .page(page)
                .pageSize(5)
                .build();
        List<Product> products = productService.searchProducts(criteria);
        System.out.println("\nPage " + page + ":");
        products.forEach(p -> System.out.println(p.getId() + " | " + p.getName() + " | " + p.getCategoryName()));

        System.out.println("\n(N)ext Page, (P)revious Page, (B)ack");
        String input = scanner.nextLine().toUpperCase();
        if (input.equals("N"))
            listProducts(page + 1);
        else if (input.equals("P") && page > 1)
            listProducts(page - 1);
    }

    private static void smartSearch() {
        System.out.print("Filter by Name (enter to skip): ");
        String name = scanner.nextLine();
        System.out.print("Min Price (enter to skip): ");
        String minStr = scanner.nextLine();
        BigDecimal min = minStr.isEmpty() ? null : new BigDecimal(minStr);

        ProductSearchCriteria criteria = ProductSearchCriteria.builder()
                .name(name)
                .minPrice(min)
                .page(1)
                .pageSize(10)
                .build();

        List<Product> results = productService.searchProducts(criteria);
        results.forEach(
                p -> System.out.println(p.getId() + " | " + p.getName() + " | Category: " + p.getCategoryName()));
    }
}
