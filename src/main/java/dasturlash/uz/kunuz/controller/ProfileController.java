package dasturlash.uz.kunuz.controller;

import dasturlash.uz.kunuz.dto.profile.ProfileFilterDto;
import dasturlash.uz.kunuz.dto.profile.ProfileDto;
import dasturlash.uz.kunuz.entity.ProfileEntity;
import dasturlash.uz.kunuz.service.ProfileService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/profile")
public class ProfileController {

    @Autowired
    private ProfileService profileService;

    @PostMapping({"", "/"})
    public ResponseEntity<?> create(@Valid @RequestBody ProfileDto dto) {
        return ResponseEntity.ok(profileService.create(dto));
    }

    @PutMapping("/admin/{id}")
    public ResponseEntity<ProfileEntity> updateByAdmin(@Valid @RequestBody ProfileDto dto, @PathVariable Integer id) {
        return ResponseEntity.ok(profileService.updateByAdmin(id, dto));
    }

    @DeleteMapping("/admin/{id}")
    public ResponseEntity<?> deleteByAdmin(@PathVariable Integer id) {
        profileService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/admin")
    public ResponseEntity<PageImpl<ProfileDto>> getList(
            @RequestParam(required = false, value = "page", defaultValue = "1") Integer page,
            @RequestParam(required = false, value = "size", defaultValue = "10") Integer size) {
        return ResponseEntity.ok(profileService.getPageList(page-1, size));
    }

    @PostMapping("/filter")
    public ResponseEntity<PageImpl<ProfileDto>> getFilter(
            @RequestBody ProfileFilterDto dto,
            @RequestParam(required = false, value = "page", defaultValue = "1") int page,
            @RequestParam(required = false, value = "size", defaultValue = "10") int size) {
        return ResponseEntity.ok(profileService.filter(dto, page - 1, size));
    }

}
