package ca.jackzavarella.courses.api.repositories;

import ca.jackzavarella.courses.CoursePdfApplication;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by Jack Zavarella on 7/5/2018. :)
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {CoursePdfApplication.class})
public class InstitutionRepositoryTests {

    @Autowired
    private InstitutionRepository repository;

//    @Test
//    public void testTest() {
//        repository.deleteAll();
//
//        Institution institution = new Institution();
//        institution.setName("University of Testing");
//
//        InstitutionMetaData data1 = new InstitutionMetaData();
//        data1.setProgramLevel("Undergraduate");
//        data1.setAcademicYear("2018-2019");
//        institution.getData().add(data1);
//
//        repository.save(institution);
//
//        InstitutionMetaData data2 = new InstitutionMetaData();
//        data1.setProgramLevel("Undergraduate");
//        data1.setAcademicYear("2017-2018");
//
//        System.out.println(repository.findInstitutionByNameAndDataContaining("University of Toasting", data2).size());
//    }
}
