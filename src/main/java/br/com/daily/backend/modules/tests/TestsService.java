package br.com.daily.backend.modules.tests;

import br.com.daily.backend.core.exceptions.GenericException;
import br.com.daily.backend.modules.tests.domain.Test;
import br.com.daily.backend.modules.tests.domain.dto.TestRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TestsService {

    @Autowired
    TestsRepository repository;

    public TestRecord createTest(TestRecord request) {
        Test test = new Test();

        test.setBannerUrl(request.bannerUrl());
        test.setText(request.text());
        test.setTitle(request.title());
        test.setQuestions(request.questions());

        return Test.mapToRecord(repository.save(test));
    }

    public List<TestRecord> fetchAllTests() {
        List<Test> tests = repository.findAll();
        List<TestRecord> response = new ArrayList<>();

        for (Test test : tests) {
            response.add(Test.mapToRecord(test));
        }

        return response;
    }

    public TestRecord updateTest(TestRecord request) {
        Optional<Test> testDB = repository.findById(request.id());

        if (testDB.isPresent()) {
            Test test = testDB.get();

            test.setBannerUrl(request.bannerUrl());
            test.setText(request.text());
            test.setTitle(request.title());
            test.setQuestions(request.questions());

            return Test.mapToRecord(repository.save(test));
        }

        throw new GenericException("TEST_NOT_FOUND", HttpStatus.NOT_FOUND);
    }

    public void deleteTest(Long id) {
        Optional<Test> testDB = repository.findById(id);

        if (testDB.isPresent()) {
            Test test = testDB.get();
            repository.delete(test);
        } else {
            throw new GenericException("TEST_NOT_FOUND", HttpStatus.NOT_FOUND);
        }
    }
}
