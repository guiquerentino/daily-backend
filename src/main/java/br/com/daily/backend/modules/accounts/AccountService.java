package br.com.daily.backend.modules.accounts;

import br.com.daily.backend.core.exceptions.GenericException;
import br.com.daily.backend.modules.accounts.domain.Patient;
import br.com.daily.backend.modules.accounts.domain.Psychologist;
import br.com.daily.backend.modules.accounts.domain.User;
import br.com.daily.backend.modules.accounts.domain.dto.LoginResponse;
import br.com.daily.backend.modules.accounts.domain.dto.UserRecord;
import br.com.daily.backend.modules.accounts.domain.enums.ROLE;
import br.com.daily.backend.modules.accounts.domain.requests.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Base64;
import java.util.Optional;

@Service
public class AccountService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private AccountUtils utils;

    @Autowired
    private PatientRepository patientRepository;

    public UserRecord createAccount(CreateAccountRequest request) {

        Optional<User> userDB = repository.findByEmail(request.email());

        if (userDB.isPresent()) {
            throw new GenericException("ACCOUNT_ALREADY_EXISTS", HttpStatus.BAD_REQUEST);
        }

        User accountWithPasswordHashed = utils.hashPassword(request);

        repository.save(accountWithPasswordHashed);
        createPatientOrPsychologist(accountWithPasswordHashed);

        return User.mapToRecord(accountWithPasswordHashed);
    }

    public LoginResponse authorizeAccount(LoginRequest request) {

        Optional<User> userDB = repository.findByEmail(request.email());

        if (userDB.isEmpty()) {
            throw new GenericException("EMAIL_NOT_FOUND", HttpStatus.NOT_FOUND);
        }

        byte[] password = utils.hashPasswordToAuthorize(request.password(), userDB.get().getPasswordSalt());

        if (Arrays.equals(password, userDB.get().getPassword())) {
            UserRecord response = User.mapToRecord(userDB.get());

            Patient patient = patientRepository.findByUserId(response.id());

            LoginResponse loginResponse = new LoginResponse();

            loginResponse.setId(response.id());
            loginResponse.setRole(response.role());
            loginResponse.setHasOnboarding(response.hasOnboarding());
            loginResponse.setEmail(response.email());
            loginResponse.setName(patient.getName());
            loginResponse.setConnectionCode(patient.getConnectionCode());
            loginResponse.setTargetList(patient.getTargets());
            loginResponse.setProfilePhoto(patient.getProfilePhoto());
            loginResponse.setMeditationExperience(patient.getMeditationExperience());
            loginResponse.setGender(patient.getGender());

            return loginResponse;
        }

        throw new GenericException("WRONG_PASSWORD", HttpStatus.UNAUTHORIZED);
    }

    public void changePassword(ChangePasswordRequest request) {
        Optional<User> userDB = repository.findByEmail(request.email());

        if (userDB.isPresent()) {

            User user = utils.hashPassword(request);

            userDB.get().setPassword(user.getPassword());
            userDB.get().setPasswordSalt(user.getPasswordSalt());

            repository.save(userDB.get());

            return;

        }
        throw new GenericException("ACCOUNT_NOT_FOUND", HttpStatus.NOT_FOUND);
    }

    public void updateProfile(ChangeProfileRequest request, Long userId) {

        Optional<User> user = repository.findById(userId);

        if (user.isPresent()) {

            User userDB = user.get();
            userDB.setEmail(request.email());

            if (request.role().equals(ROLE.PATIENT)) {

                Patient patientDb = patientRepository.findByUserId(userId);
                patientDb.setName(request.name());
                patientDb.setGender(request.gender());
                patientDb.setProfilePhoto(request.profilePhoto());

                patientRepository.save(patientDb);
            }
            /*
            TODO: PSYCHOLOGIST UPDATE
             */
        } else {
            throw new GenericException("ACCOUNT_NOT_FOUND", HttpStatus.NOT_FOUND);
        }
    }

    private void createPatientOrPsychologist(User user) {

        if (user.getRole().equals(ROLE.PATIENT)) {
            Patient patient = new Patient();
            patient.setUser(user);

            patientRepository.save(patient);
        }

        /*
        TODO: PSYCHOLOGIST LINE CREATE
         */

    }

    public void finishPatientOnboarding(OnboardingRequest request, Long userId) {

        Optional<User> user = repository.findById(userId);

        if(user.isPresent()){
            user.get().setHasOnboarding(true);
            repository.save(user.get());
        }

        Patient patient = patientRepository.findByUserId(userId);

        patient.setAge(request.age());
        patient.setGender(request.gender());
        patient.setName(request.name());
        patient.setTargets(request.targets());
        patient.setMeditationExperience(request.meditationExperience());

        patientRepository.save(patient);
    }

    public void updateProfilePhoto(Long userId, String base64){
        Patient patient = patientRepository.findByUserId(userId);

        if (base64 != null && !base64.isEmpty()) {
            try {
                byte[] photoBytes = Base64.getDecoder().decode(base64);
                patient.setProfilePhoto(photoBytes);
                patientRepository.save(patient);
            } catch (IllegalArgumentException e) {
                throw new GenericException("BASE_64_ERROR", HttpStatus.BAD_REQUEST);
            }
        }

    }

}
