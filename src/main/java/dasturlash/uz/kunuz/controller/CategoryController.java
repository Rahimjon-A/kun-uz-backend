package dasturlash.uz.kunuz.controller;


import dasturlash.uz.kunuz.dto.CategoryDto;
import dasturlash.uz.kunuz.dto.LangResponse;
import dasturlash.uz.kunuz.entity.CategoryEntity;
import dasturlash.uz.kunuz.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping({"", "/"})
    public ResponseEntity<CategoryDto> create(@Valid @RequestBody CategoryDto dto) {
        //  Qanday qilib 201 jo'natsa bo'ladi yangi url bilan
        return ResponseEntity.ok(categoryService.create(dto));
    }

    @PutMapping({"", "/"})
    public ResponseEntity<CategoryDto> update(@Valid @RequestBody CategoryDto dto) {
        return ResponseEntity.ok(categoryService.update(dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        categoryService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/admin")
    public ResponseEntity<List<CategoryEntity>> getAllByAdmin() {
        return ResponseEntity.ok(categoryService.getAllByAdmin());
    }

    @GetMapping("/lang/{ln}")
    public ResponseEntity<List<LangResponse>> getAll(@PathVariable String ln) {
        return ResponseEntity.ok(categoryService.getRegionsByLang(ln));
    }

}
