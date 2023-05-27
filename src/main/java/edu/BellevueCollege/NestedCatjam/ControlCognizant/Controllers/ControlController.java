package edu.BellevueCollege.NestedCatjam.ControlCognizant.Controllers;

import edu.BellevueCollege.NestedCatjam.ControlCognizant.Entities.HitrustControl;
import edu.BellevueCollege.NestedCatjam.ControlCognizant.Entities.NistControl;
import edu.BellevueCollege.NestedCatjam.ControlCognizant.Repositories.HitrustRepository;
import edu.BellevueCollege.NestedCatjam.ControlCognizant.Repositories.NistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.*;

@RestController
@RequestMapping
@Transactional
public class ControlController {
    @Autowired
    private HitrustRepository hitrustRepository;

    @Autowired
    private NistRepository nistRepository;

    @Transactional
    @PostMapping("/api/v1/hitrust_control/create")
    public ResponseEntity<HitrustControl> createHitrustControl(@RequestBody HitrustControl control) {
        try {
            HitrustControl savedHitrustControl = hitrustRepository.save(control);
            return new ResponseEntity<>(savedHitrustControl, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @Transactional
    @PostMapping("/api/v1/nist_control/create")
    public ResponseEntity<NistControl> createNistControl(@RequestBody NistControl control) {
        try {
            NistControl savedNistControl = nistRepository.save(control);
            return new ResponseEntity<>(savedNistControl, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Transactional
    @GetMapping("/api/v1/hitrust_control/get_one/{id}")
    public ResponseEntity<Object> getHitrustControlById(@PathVariable long id) {
        try {
            return new ResponseEntity<>(hitrustRepository.findById(id), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Transactional
    @GetMapping("/api/v1/hitrust_control/satisfied_requirements")
    public ResponseEntity<Object> getSatisfiedHitrustControls(Boolean satisfied) {
        try {
            return new ResponseEntity<>(hitrustRepository.findAllBySatisfied(satisfied), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Transactional
    @GetMapping("/api/v1/hitrust_control/mapping/{nistName}")
    public ResponseEntity<Object> getHitrustControlsByNistMapping(@PathVariable String nistName) {
        try {
            NistControl nist = nistRepository.findByControlName(nistName);
            Set<HitrustControl> mappings = nist.getHitrustControls();
            return new ResponseEntity<>(mappings, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Transactional
    @GetMapping("/api/v1/hitrust_control/category/{control_category}")
    public ResponseEntity<Object> getHitrustControlByControlCategory(@PathVariable String control_category) {
        try {
            return new ResponseEntity<>(hitrustRepository.findAllByControlCategory(control_category), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Transactional
    @GetMapping("/api/v1/hitrust_control/function/{control_function}")
    public ResponseEntity<Object> getHitrustControlByControlFunction(@PathVariable String control_function) {
        try {
            return new ResponseEntity<>(hitrustRepository.findAllByControlFunction(control_function), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // NIST ENDPOINTS
    @Transactional
    @GetMapping("/api/v1/nist_control/satisfied_requirements")
    public ResponseEntity<Object> getSatisfiedNistControls(Boolean satisfied) {
        try {
            return new ResponseEntity<>(nistRepository.findAllBySatisfied(satisfied), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Transactional
    @GetMapping("/api/v1/controls/all_satisfied_controls")
    public ResponseEntity<Object> getAllSatisfiedControlls() {
        try {
            List<Object> allSatisfiedControls = new ArrayList<>();
            allSatisfiedControls.addAll(hitrustRepository.findAllBySatisfied(true));
            allSatisfiedControls.addAll(nistRepository.findAllBySatisfied(true));
            return new ResponseEntity<>(allSatisfiedControls, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Transactional
    @GetMapping("/api/v1/controls/all_unsatisfied_controls")
    public ResponseEntity<Object> getAllUnsatisfiedControls() {
        try {
            List<Object> allSatisfiedControls = new ArrayList<>();
            allSatisfiedControls.addAll(hitrustRepository.findAllBySatisfied(false));
            allSatisfiedControls.addAll(nistRepository.findAllBySatisfied(false));
            return new ResponseEntity<>(allSatisfiedControls, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Transactional
    @GetMapping("/api/v1/nist_control/get_all_controls")
    public ResponseEntity<Object> getAllNistControls() {
        try {
            ResponseEntity<Object> response = new ResponseEntity<>(nistRepository.findAll(), HttpStatus.OK);
            return response;
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Transactional
    @GetMapping("/api/v1/hitrust_control/get_all_controls")
    public ResponseEntity<Object> getAllHitrustControls() {
        try {
            ResponseEntity<Object> response = new ResponseEntity<>(hitrustRepository.findAll(), HttpStatus.OK);
            return response;
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Transactional
    @GetMapping("/api/v1/control/get_all_controls")
    public ResponseEntity<Object> getAllControls() {
        try {
            List<HitrustControl> hitrustControls = hitrustRepository.findAll();
            List<NistControl> nistControls = nistRepository.findAll();
            List<Object> allControls = new ArrayList<>();
            allControls.addAll(hitrustControls);
            allControls.addAll(nistControls);
            return new ResponseEntity<>(allControls, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Transactional
    @GetMapping("/api/v1/nist_control/mapping/{hitrust_mapping}")
    public ResponseEntity<Object> getNistControlByHitrustMapping(@PathVariable String hitrust_mapping) {
        try {
            HitrustControl hitrust = hitrustRepository.findByControlName(hitrust_mapping);
            Set<NistControl> mappings = hitrust.getNistControls();
            return new ResponseEntity<>(mappings, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Transactional
    @GetMapping("/api/v1/nist_control/by_category/{control_category}")
    public ResponseEntity<Object> getNistControlByControlCategory(@PathVariable String control_category) {
        try {
            return new ResponseEntity<>(nistRepository.findAllByControlCategory(control_category), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Transactional
    @GetMapping("/api/v1/nist_control/by_function/{control_function}")
    public ResponseEntity<Object> getNistControlByControlFunction(@PathVariable String control_function) {
        try {
            return new ResponseEntity<>(nistRepository.findAllByControlFunction(control_function), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Transactional
    @GetMapping("/api/v1/nist_control/get/{control_id}")
    public ResponseEntity<Object> getNistControlByControlId(@PathVariable long control_id) {
        try {
            return new ResponseEntity<>(nistRepository.findById(control_id), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @Transactional
    @PostMapping("/api/v1/nist_control/confirm_nist_compliance/{nistControl}")
    public ResponseEntity<Object> confirmNistCompliance(@PathVariable long nistControl, String orgId) {
        try {
            NistControl  control = nistRepository.findById(nistControl);
            control.setSatisfied(true);
            nistRepository.save(control);
            for (HitrustControl hitrustControl : control.getHitrustControls()) {
                hitrustControl.setSatisfied(true);
                hitrustRepository.save(hitrustControl);
            }
            return new ResponseEntity<>(nistControl, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Transactional
    @GetMapping("/api/v1/functions")
    public ResponseEntity<Object> getFunctions() {
        try {
            List<Object> controls = new ArrayList<>();
            controls.addAll(hitrustRepository.findAll());
            controls.addAll(nistRepository.findAll());
            List<String> functions = new ArrayList<>();
            for (Object control : controls) {
                if (control instanceof HitrustControl) {
                    HitrustControl hitrustControl = (HitrustControl) control;
                    if (!functions.contains(hitrustControl.getControlFunction())) {
                        functions.add(hitrustControl.getControlFunction());
                    }
                } else if (control instanceof NistControl) {
                    NistControl nistControl = (NistControl) control;
                    if (!functions.contains(nistControl.getControlFunction())) {
                        functions.add(nistControl.getControlFunction());
                    }
                }
            }
            return new ResponseEntity<>(functions, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Transactional
    @GetMapping("/api/v1/categories")
    public ResponseEntity<Object> getCategories() {
        try {
            List<Object> controls = new ArrayList<>();
            controls.addAll(hitrustRepository.findAll());
            controls.addAll(nistRepository.findAll());
            List<String> categories = new ArrayList<>();
            for (Object control : controls) {
                if (control instanceof HitrustControl) {
                    HitrustControl hitrustControl = (HitrustControl) control;
                    if (!categories.contains(hitrustControl.getControlCategory())) {
                        categories.add(hitrustControl.getControlCategory());
                    }
                } else if (control instanceof NistControl) {
                    NistControl nistControl = (NistControl) control;
                    if (!categories.contains(nistControl.getControlCategory())) {
                        categories.add(nistControl.getControlCategory());
                    }
                }
            }
            return new ResponseEntity<>(categories, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Transactional
    @PutMapping("/api/v1/hitrust_control/update/{id}")
    public ResponseEntity<HitrustControl> updateHitrustControl(@PathVariable("id") long id, @RequestBody Map<String, Object> payload) {
        try {
            // Retrieve the existing HitrustControl entity by its ID
            Optional<HitrustControl> optionalHitrustControl = Optional.ofNullable(hitrustRepository.findById(id));
            if (optionalHitrustControl.isEmpty()) {
                return ResponseEntity.notFound().build();
            }

            // Update the properties of the HitrustControl entity
            HitrustControl existingHitrustControl = optionalHitrustControl.get();
            existingHitrustControl.setControlFunction((String) payload.get("controlFunction"));
            existingHitrustControl.setControlCategory((String) payload.get("controlCategory"));
            existingHitrustControl.setControlName((String) payload.get("controlName"));
            existingHitrustControl.setControlDescription((String) payload.get("controlDescription"));

            // Update the mappings
            List<String> nistMappings = (List<String>) payload.get("nistMappings");
            Set<NistControl> nistControls = new HashSet<>();
            for (String mapping : nistMappings) {
                NistControl nistControl = nistRepository.findByControlName(mapping);
                if (nistControl != null) {
                    nistControls.add(nistControl);
                }
            }
            existingHitrustControl.setNistControls(nistControls);

            // Save the updated HitrustControl entity
            HitrustControl updatedHitrustControl = hitrustRepository.save(existingHitrustControl);

            return ResponseEntity.ok(updatedHitrustControl);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


    @Transactional
    @PutMapping("/api/v1/nist_control/update/{id}")
    public ResponseEntity<NistControl> updateNistControl(@PathVariable("id") long id, @RequestBody Map<String, Object> payload) {
        try {
            // Retrieve the existing NistControl entity by its ID
            Optional<NistControl> optionalNistControl = Optional.ofNullable(nistRepository.findById(id));
            if (optionalNistControl.isEmpty()) {
                return ResponseEntity.notFound().build();
            }

            // Update the properties of the NistControl entity
            NistControl existingNistControl = optionalNistControl.get();
            existingNistControl.setControlFunction((String) payload.get("controlFunction"));
            existingNistControl.setControlCategory((String) payload.get("controlCategory"));
            existingNistControl.setControlName((String) payload.get("controlName"));
            existingNistControl.setControlDescription((String) payload.get("controlDescription"));

            // Update the mappings
            List<String> hitrustMappings = (List<String>) payload.get("hitrustMappings");
            Set<HitrustControl> hitrustControls = new HashSet<>();
            for (String mapping : hitrustMappings) {
                HitrustControl hitrustControl = hitrustRepository.findByControlName(mapping);
                if (hitrustControl != null) {
                    hitrustControls.add(hitrustControl);
                }
            }
            existingNistControl.setHitrustControls(hitrustControls);

            // Save the updated NistControl entity
            NistControl updatedNistControl = nistRepository.save(existingNistControl);

            return ResponseEntity.ok(updatedNistControl);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
