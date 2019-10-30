import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

//@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class UnitTest1Test {

//    Junit odpowiada za cykl zycia testu, towrzy instancje obiektu UnitTest1Test
//    Metody powinny byc od siebie niezalezne, kolejnosc ich wywolania nie powinna miec znaczenia,
//    zmienne w metodzie nr.1 nie powinna miec wplywu na zmienna w metodzie nr.2.
//    W Junit 5 domyslnie jest to niemozliwe, poniewaz tworzy on osobno nowa instancje dla kazdej metody

//    @BeforeAll - uruchamiana jest metoda przed wszystkimi, wykonuje sie nawet przed utworzeniem instacji klasy UnitTest1Test, dlatego metoda zwiazana z BeforeAll musi byc static
//    @AfterAll - uruchamiana jest moetoda po wszystkich
//    @BeforeEach - uruchamiana jest metoda przed kazda inna
//    @AfterEach - uruchamiana jest metoda po kazdej innej
//    @TestInstance(TestInstance.Lifecycle.PER_CLASS) - tworzy jedna instacje obiektu dla wszystkich metod. Wtedy metoda zwiazana z @BeforeAll nie musi byc static
//    @DisplayName() - zamiast nazwy metody bedzie widnialo to co napiszemy w nawiasach
//    @Disabled - omija metode
//    @EnabledOnOs - wyswietli tylko jesli jest to np. system operacyjny Linux etc.
//    @Nested - tworzy zbior testow w 1, ktory mozemy dzielic wewnatrz, mamy wiekszy wglad w kod niz w assertAll
//    @RepeatedTest() - powtarza test tyle razy jaka jest wartosc w nawiasach
//
//    metoda assumeTrue() jesli jest true to test sie wykona, jesli false to zostanie ominieta jak w @Disabled.
//    W nawiasy mozna umiescic jakas zmienna ktora w zaleznosci od innych moze przyjmowac stany 1 i 0
//    metoda assertAll( ()-> ()-> ... ) - wiele akcji w 1 tescie (w Junit 4 nie bylo to mozliwe)


    UnitTest1 test;

    //Metoda sprawia ze nie musimy powielac kodu
    @BeforeEach
    public void init() {
        test = new UnitTest1();
    }

    @Test
    @DisplayName("Adding test method")
    public void testAddNumber() {
        //UnitTest1 test = new UnitTest1(); - nie musimy tworzyc juz obiektu klasy poniewaz przed kazda metoda wykonuje sie metoda init()
        int result = test.addNumber(5, 8);
        assertEquals(13,result);
    }

    @Test
    @DisplayName("This test specialy have mistakes")
    public void testAddNumber2() {
        int result = test.addNumber(5, 8);
        assertEquals(-3, result, "The addNumber method should add 2 numbers");
    }

    @Test
    public void testCircleArea() {
        assumeTrue(true);
        //assumeTrue(false);
        assertEquals(Math.PI*100,test.CircleArea(10), "Should return circle area");
    }

    @Test
    public void testDivideNumber() {
        assertThrows(ArithmeticException.class, () -> test.divideNumber(1,0), "Divide by zero should throw"); //wyrazenia lambda nie byly uzywane w junit4
    }

    @Test
    @Disabled
    public void missMethod(){
        fail("This not gonna happend");
    }

    @Test
    @EnabledOnOs(OS.LINUX)
    public void forLinuxOnly(){
        System.out.println("You have Linux");
    }

    @Test
    @DisplayName("Multiply method")
    public void testMultiplyNumber(){
        assertAll(
                () -> assertEquals(4, test.multiplyNumber(2,2)),
                () -> assertEquals(-4, test.multiplyNumber(-2,2)),
                () -> assertEquals(0, test.multiplyNumber(0,2))
        );
    }

    @Nested
    class AddTest{
        @Test
        @DisplayName("Adding + in nested")
        public void adding(){
            assertEquals(2,test.addNumber(1,1), "buuu");
        }

        @Test
        @DisplayName("Adding - in nested")
        public void addminus(){
            assertEquals(-4,test.addNumber(-2,-2),"jeeee");
        }
    }

    @RepeatedTest(3)
    public void repeatTest(RepetitionInfo repetitionInfo){ //repeptitionInfo umozliwa dostanie sie do poszczegolnych powtorzen i wykonanie dzialania
        if(repetitionInfo.getCurrentRepetition()==1){
            System.out.println("Wykonano test po raz 1");
        }
        else if(repetitionInfo.getCurrentRepetition()==2){
            System.out.println("wykonano test po raz 2");
        }
        assertEquals(2,test.addNumber(1,1));
    }

}