package dasturlash.uz.kunuz.controller;

import dasturlash.uz.kunuz.dto.result.LangMapper;
import dasturlash.uz.kunuz.dto.result.LangResponse;
import dasturlash.uz.kunuz.dto.SectionDto;
import dasturlash.uz.kunuz.entity.SectionEntity;
import dasturlash.uz.kunuz.enums.Lang;
import dasturlash.uz.kunuz.service.SectionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/section")
public class SectionController {

    @Autowired
    private SectionService sectionService;

    @PostMapping({"", "/"}) //   localhost:8080/api/v1/region
    public ResponseEntity<SectionDto> create(@Valid @RequestBody SectionDto dto) {
//        Qanday qilib 201 jo'natsa bo'ladi yangi url bilan
        return ResponseEntity.ok(sectionService.create(dto));
    }

    @PutMapping({"", "/"})
    public ResponseEntity<SectionDto> update(@Valid @RequestBody SectionDto dto) {
        return ResponseEntity.ok(sectionService.update(dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        sectionService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/admin")
    public ResponseEntity<PageImpl<SectionEntity>> getAllByAdmin(
            @RequestParam(required = false, value = "page", defaultValue = "1") Integer page,
            @RequestParam(required = false, value = "size", defaultValue = "5") Integer size) {
        return ResponseEntity.ok(sectionService.getAllByAdmin(page-1, size));
    }

    @GetMapping("/lang")
    public ResponseEntity<List<LangMapper>> getAll(@RequestHeader(name = "Accept-Language", defaultValue = "UZ") Lang ln) {
        return ResponseEntity.ok(sectionService.getRegionsByLang(ln));
    }
}
