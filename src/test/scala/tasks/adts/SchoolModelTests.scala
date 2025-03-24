package tasks.adts

import org.junit.*
import org.junit.Assert.*
import tasks.adts.SchoolModel.*
import u03.extensionmethods.Sequences.*
import  u03.Optionals.*

/* Tests should be clear, but note they are expressed independently of the
   specific implementation
*/

class SchoolModelTests:

  // Choice of implementation to test
  val schoolADT: SchoolModule = BasicSchoolModule
  import schoolADT.*
  import Sequence.*

  // From now, everything is independent of specific implementation of Complex

  @Test def testTeacher(): Unit =
    assertEquals("Mirko", teacher("Mirko"))

  @Test def testCourse(): Unit =
    assertEquals("PPS", course("PPS"))

  @Test def testEmptySchool(): Unit =
    assertEquals(Nil(), emptySchool)

  @Test def testSetTeacherToCourses(): Unit =
    assertEquals(Nil(),emptySchool.coursesOfATeacher(teacher("John"))) // => )
    assertEquals(Cons("Math", Nil()),emptySchool
    .setTeacherToCourse(teacher("John"), course("Math"))
    .coursesOfATeacher(teacher("John")))

    assertEquals(Cons("Italian", Cons("Math", Nil())),emptySchool
    .setTeacherToCourse(teacher("John"), course("Math"))
    .setTeacherToCourse(teacher("John"), course("Italian"))
    .coursesOfATeacher(teacher("John")))

  @Test def testCoursesInSchool(): Unit =
    assertEquals(Nil(), emptySchool.courses)
    assertEquals(Cons("Math", Nil()), emptySchool.setTeacherToCourse(teacher("John"), course("Math")).courses)
    assertEquals(Cons("Math", Cons("Italian", Nil())),emptySchool
      .setTeacherToCourse(teacher("John"), course("Italian"))
      .setTeacherToCourse(teacher("John"), course("Math")).courses)

  @Test def testNoDuplicateInCourses(): Unit =
    assertEquals(Cons("Math", Cons("Italian", Nil())), emptySchool
      .setTeacherToCourse(teacher("John"), course("Italian"))
      .setTeacherToCourse(teacher("John"), course("Math"))
      .setTeacherToCourse(teacher("Billy"), course("Math")).courses)

  @Test def testTeachersInSchool(): Unit =
    assertEquals(Cons("Billy",Cons("John",Nil())),emptySchool
    .setTeacherToCourse(teacher("John"), course("Math"))
      .setTeacherToCourse(teacher("Billy"), course("Italian")).teachers)

  @Test def testNoDuplicateInTeachers(): Unit =
    assertEquals(Cons("John", Cons("Billy", Nil())), emptySchool
      .setTeacherToCourse(teacher("John"), course("Math"))
      .setTeacherToCourse(teacher("Billy"), course("Italian"))
      .setTeacherToCourse(teacher("John"), course("Italian")).teachers)

  @Test def testCoursesOfATeacher(): Unit =
    assertEquals(Nil(),emptySchool.coursesOfATeacher(teacher("John")))
    assertEquals(Cons("Math", Nil()),emptySchool
        .setTeacherToCourse(teacher("John"), course("Math"))
        .coursesOfATeacher(teacher("John")))
    assertEquals(Cons("Italian", Cons("Math", Nil())), emptySchool
        .setTeacherToCourse(teacher("John"), course("Math"))
        .setTeacherToCourse(teacher("John"), course("Italian"))
        .coursesOfATeacher(teacher("John")))

  @Test def testSchoolHasTeacher(): Unit =
    assertEquals(false, emptySchool.hasTeacher("John"))
    assertEquals(true, emptySchool
        .setTeacherToCourse(teacher("John"), course("Math"))
        .hasTeacher("John"))

  @Test def testSchoolHasCourse(): Unit =
    assertEquals(false, emptySchool.hasCourse("Math"))
    assertEquals(true, emptySchool
        .setTeacherToCourse(teacher("John"), course("Math"))
        .hasCourse("Math"))