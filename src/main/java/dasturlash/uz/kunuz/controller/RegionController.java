package dasturlash.uz.kunuz.controller;

import dasturlash.uz.kunuz.dto.LangResponse;
import dasturlash.uz.kunuz.dto.RegionDto;
import dasturlash.uz.kunuz.entity.RegionEntity;
import dasturlash.uz.kunuz.service.RegionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/region")
public class RegionController {

    @Autowired
    private RegionService regionService;

    @PostMapping({"", "/"}) //   localhost:8080/api/v1/region
    public ResponseEntity<RegionDto> create(
            @Valid @RequestBody RegionDto dto,
            UriComponentsBuilder uriBuilder) {

        RegionDto regionDto = regionService.create(dto);
        Integer id = regionService.getIdByKey(dto.getRegionKey());

//        Qanday qilib 201 jo'natsa bo'ladi yangi url bilan
        URI uri = uriBuilder.path("/api/v1/region/{id}").buildAndExpand(id).toUri();

        return ResponseEntity.created(uri).body(regionDto);
    }

    @PutMapping({"", "/"})
    public ResponseEntity<RegionDto> update(@Valid @RequestBody RegionDto dto) {


        return ResponseEntity.ok(regionService.update(dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        regionService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/admin")
    public ResponseEntity<List<RegionEntity>> getAllByAdmin() {
        return ResponseEntity.ok(regionService.getAllByAdmin());
    }

    @GetMapping("/lang/{ln}")
    public ResponseEntity<List<LangResponse>> getAll(@PathVariable String ln) {
        return ResponseEntity.ok(regionService.getRegionsByLang(ln));
    }
}

