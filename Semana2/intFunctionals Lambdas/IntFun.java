import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.function.*;
import java.util.stream.Collectors;

public class IntFun {

	public static void main(String[] args) {

        System.out.println("///// SUPPLIERS /////");

        
//		This Supplier generates a random number
        Supplier<Integer> randomNumber = () -> new Random().nextInt(10);
        Integer randomInt = randomNumber.get();
        System.out.println("RANDOM NUMBER SUPPLIER: " + randomInt);

        
//		This supplier calls upon the method of a class using the 
//      instance of that class
        class SupplierHello {
        public String hello() {
        	return("Buenos dias Friendo");
        	}
        }
        SupplierHello Helloinstance = new SupplierHello();
        Supplier<String> Supplierforgreeting = Helloinstance::hello;
        String result = Supplierforgreeting.get();
        System.out.println("CLASS METHOD CALL SUPPLIER: " + result);

        
//		This supplier calls directly to a lambda, to get a string
        Supplier<String> Lambdasupplier = () -> "lambda string";
        String Lambda = Lambdasupplier.get();
        System.out.println("LAMBDA SUPPLIER: " + Lambda);

        ;
//		This supplier calls for the arraylist that is created in another supplier
        class SupplierArrayList<T> {
            public Supplier<List<T>> supplier() {
                return ArrayList::new;
            }
        }
        SupplierArrayList<String> arrayListSupplier = new SupplierArrayList<>();
        Supplier<List<String>> listSupplier = arrayListSupplier.supplier();
        List<String> list = listSupplier.get();
        list.add("Supplier");
        list.add("get");
        list.add("Supplier");
        System.out.println("ARRAYLIST SUPPLIER: " + list);
        
////////////////////////////////////////////////////////////////////////////////

        System.out.println();
        System.out.println("///// CONSUMERS /////");

//		Basic consumer that uses a lambda to print a string
        Consumer<String> basiconsumer = s -> System.out.println(s);
        basiconsumer.accept("STRING CONSUMER: coolbeans");
        
        
//		Consumer which takes in an integer and prints out a multiplication
        Consumer<Integer> multi = t -> System.out.println("INTEGER CONSUMER: "+t*t);
        multi.accept(5);
        
        
//		Consumer which takes in a generic and prints the objects
        Consumer<Object> Genericonsumer = T -> System.out.println(T);
        Genericonsumer.accept("GENERIC CONSUMER:");
        Genericonsumer.accept(123);
        Genericonsumer.accept(45.67);
        
        
//		Consumer which uses a constructor to create a new student.
        class Student {
            String name;
            int age;
            Student(String name, int age) {
                this.name = name;
                this.age = age;
            }
            @Override
            public String toString() {
                return "Person{name='" + name + "', age=" + age + '}';
            }
        }
        
        Consumer<Student> describePersonConsumer = Student -> 
        System.out.println("CONSTRUCTOR CONSUMER: "+Student.name + " tiene " + Student.age + " años.");
        describePersonConsumer.accept(new Student("Ever", 27));
        
////////////////////////////////////////////////////////////////////////////////

        System.out.println();
        System.out.println("///// BICONSUMERS /////");
       
        
//		This BiConsumer takes a pair of strings and prints a message.
        BiConsumer<String, String> SC = (first, second) ->
        System.out.println("STRING BICONSUMER: "+first+" "+second);
        SC.accept("BiConsumer", "Activate!");
        
        
//		This BiConsumer takes a string and an integer and prints a message.
        BiConsumer<String, Integer> BT = (taco, cantidad) ->
        System.out.println("COMBINED BICONSUMER: tu orden es de "+cantidad + " tacos de " + taco);
        BT.accept("trompo", 5);
        
        
//		This BiConsumer takes in two classes and prints them out
        class Ciudadano {
            String nombre;
            int edad;

            Ciudadano(String nombre, int edad) {
                this.nombre = nombre;
                this.edad = edad;
            	}

            @Override
            public String toString() {
                return "CLASSES BICONSUMER: Nombre=" + nombre + ", edad=" + edad;
            	}
        	}

        class Direccion {
            String municipio;
            Direccion(String municipio) {
                this.municipio = municipio;
            }
            @Override
            public String toString() {
                return "Municipio=" + municipio;
            	}
        	}
        BiConsumer<Ciudadano, Direccion> printPersonAndAddress = (ciudadano, direccion) -> {
        System.out.println(ciudadano);
        System.out.println(direccion);
        };
        Ciudadano ciudadano = new Ciudadano("Jose", 32);
        Direccion direccion = new Direccion("Mitras");
        printPersonAndAddress.accept(ciudadano, direccion);
        
        
//		This BiConsumer takes in a string and a supplier to roll a dice.
        Supplier<Integer> DiceSupplier = () -> new Random().nextInt(6);
        BiConsumer<String, Supplier<Integer>> MD = (string, diceSupplier) -> {
        Integer random = diceSupplier.get();
        System.out.println("BICONSUMER WITH SUPPLIER: "+string+" "+random);
        };
        MD.accept("Tiras el dado y sacas:", DiceSupplier);

////////////////////////////////////////////////////////////////////////////////
        
        System.out.println();
        System.out.println("///// PREDICATES /////");
		
        
//		Predicate that checks if String is not empty
        String testString = "string check";
        Predicate<String> isNonEmpty = str -> !str.isEmpty();
        System.out.println("STRING PREDICATE: is the string not empty? " + isNonEmpty.test(testString));

        
//		Predicate checks if an integer is positive
        Integer numero = -11;
        Predicate<Integer> positive = check -> numero >= 0;
        System.out.println("INTEGER PREDICATE: is "+numero+" positive? "+positive.test(numero));
        
        
//		Predicate checks if a list has names that start with E using a lambdas
        Predicate<String> StartE = name -> name.startsWith("E");
        List<String> names = Arrays.asList("Ever", "Elie", "8Earl", "mina");
        List<String> Enames = names.stream()
                .filter(StartE)
                .collect(Collectors.toList());
        System.out.println("STRING PREDICATE: Names that starts with letter 'E': " + Enames);
        
        
//		The predicate takes in the "estudiante" class and checks if he passes.
        class Estudiante{
        	String nombre;
        	int calificacion;
        	public Estudiante(String nombre, int calificacion) {
        		this.nombre = nombre;
        		this.calificacion = calificacion;
        	}
        	@Override
        	public String toString() {
        		return "Empleado [nombre=" + nombre + ", calificacion=" + calificacion + "]";
        	}
        }
        Estudiante e1 = new Estudiante("Pedro",67);
		Predicate<Estudiante> pre0 =  e -> e.calificacion >=70;
		boolean paso = pre0.test(e1);
		System.out.println("CLASS PREDICATE: El estudiante paso? "+paso);

////////////////////////////////////////////////////////////////////////////////
       
        System.out.println();
        System.out.println("///// BIPREDICATES /////");

//		This bipredicate takes two strings to check if they have the same length
        BiPredicate<String, String> sameLength = (str1, str2) -> str1.length() == str2.length();
        String strA = "Godliness";
        String strB = "Cleanliness ";
        System.out.println("STRING BIPREDICATE: Do both strings have the same lenght? " + sameLength.test(strA, strB));
        
        
//		The bipredicate takes a string and an integer and checks if the string is and if the price is right
        BiPredicate<String, Integer> platanoCaro = (producto, precio) -> 
        producto.toLowerCase().contains("platano") && precio < 36;
        String producto = "Platano Chiapas";
        Integer precio = 30;
        System.out.println("MIXED BIPREDICATE: Is it a banana and at fair price? " + platanoCaro.test(producto, precio));
    
    
//		Bipredicate takes in a list to check if its within the range of an integer
        BiPredicate<List<Integer>, Integer> Listinrange = (listado, rango) ->
        listado.stream().allMatch(n -> n <= rango && !listado.isEmpty());
        List<Integer> numeros = List.of(3, 5, 9);
        Integer rango = 10;
        System.out.println("LIST BIPREDICATE: Is the list within the range of "+rango+"? " + Listinrange.test(numeros, rango));
        
        
//		Bipredicate takes in a predicate to check if a list contains a failing note
        Predicate<Integer> pass = number -> number >= 70;
        BiPredicate<List<Integer>, Predicate<Integer>> POF = (calist, predicate) -> 
        calist.stream().anyMatch(predicate.negate());
        List<Integer> calist = List.of(50, 99, 100, 85);
        System.out.println("PREDICATE BIPREDICATE: Did someone fail? " + POF.test(calist, pass));
        
////////////////////////////////////////////////////////////////////////////////

        System.out.println();
        System.out.println("///// FUNCTIONS /////");

//		Function takes in a string and gives it back in CAPS
        Function<String, String> CAPS = s -> s.toUpperCase();
        String text = "i want to be big";
        System.out.println("STRING FUNCTION: " + CAPS.apply(text));
        
        
//		Function takes it a double and converts it to an integer, removing decimals
        Function<Double, Integer> integenator = d -> (int) Math.floor(d);     
        Double number = 15.99;
        System.out.println("MIXED FUNCTION: " + integenator.apply(number));
        
        
//		Function takes in a set of integers and elevates to the square then collects them to another set
        Function<Set<Integer>, Set<Integer>> alcuadrado = set -> 
        set.stream().map(x -> x * x).collect(Collectors.toSet());
        Set<Integer> Num = Set.of(2, 4, 5, 7, 8);
        Set<Integer> Cuad = alcuadrado.apply(Num);
    	System.out.println("SET FUNCTION: " + Cuad);
    	
    	
//		Function takes in a class and takes to call its to string function
        class Personaje {
            String nombre;
            int nivel;
            Personaje(String nombre, int nivel) {
                this.nombre = nombre;
                this.nivel = nivel;
            }
            @Override
            public String toString() {
                return nombre + " nivel: " + nivel;
            }
        }
        Function<Personaje, String> TS = personaje -> personaje.toString();
        Personaje personaje = new Personaje("Mage", 45);
        String pers = TS.apply(personaje);
        System.out.println("CLASS FUNCTION: " + pers);

////////////////////////////////////////////////////////////////////////////////

        System.out.println();
        System.out.println("///// BIFUNCTIONS /////");

//		BiFunction takes in two integers and divides them
        BiFunction<Integer, Integer, Double> div = (a, b) -> (double) a / b;
        Integer n1 = 7;
        Integer n2 = 3;
        Double r = div.apply(n1, n2);
        
        System.out.println("INTEGER BIFUNCTION: "+n1+"/"+n2+"= " + r); 
        
        
//		BiFunction takes in a string and an integer and returns a employee object
        class Empleado {
            String nombre;
            Integer salario;
            Empleado(String nombre, Integer salario) {
                this.nombre = nombre;
                this.salario = salario;
            }
            @Override
            public String toString() {
                return "El empleado "+nombre + " gana " + salario+ " pesos al mes";
            }
        }
        BiFunction<String, Integer, Empleado> NE = (n, s) -> new Empleado(n, s);
        String n = "Federico";
        Integer s = 17000;
        Empleado empleado = NE.apply(n, s);
        System.out.println("RETURN CLASS BIFUNCTION: " + empleado); 
        
        
//		BiFunction takes in two lists and returns a third one with their values combined
        BiFunction<List<Integer>, List<Integer>, List<Integer>> combinar = (list1, list2) -> {
            List<Integer> list3 = new ArrayList<>(list1);
            list3.addAll(list2);
            return list3;
        };
        List<Integer> list1 = List.of(1, 2, 3);
        List<Integer> list2 = List.of(4, 5, 6);
        List<Integer> list3 = combinar.apply(list1, list2);
        System.out.println("LIST BIFUNCTION: " + list3);
        
        
//		Bifunction takes in a map and an integer and outputs a predicate to check for raise in employees
        BiFunction<Map<String, Integer>, Integer, Predicate<String>> AP = (map, minimo) ->
        item -> map.getOrDefault(item, 0) >= minimo;
        Map<String, Integer> sueldos = new HashMap<>();
        sueldos.put("Mario", 20000);
        sueldos.put("Pepe", 9500);
        sueldos.put("Luigi", 14000);
        Integer minimo = 10000;
        Predicate<String> ocupaumento = AP.apply(sueldos, minimo);
        System.out.println("MAP BIFUNCTION: ");
        System.out.println("Does Mario need a raise? " + ocupaumento.test("Mario"));
        System.out.println("Does Pepe need a raise? " + ocupaumento.test("Pepe")); 
        System.out.println("Does Luigi need a raise? " + ocupaumento.test("Luigi")); 

////////////////////////////////////////////////////////////////////////////////

        System.out.println();
        System.out.println("///// UNARYOPERATORS /////");

//		UnaryOperator takes it an integer and duplicates it
        UnaryOperator<Integer> duplicate = num -> num * 2;
        Integer start = 10;
        Integer end = duplicate.apply(start);
        System.out.println("INTEGER UNARYOPERATOR: " + end); 
        
        
//		UnaryOperator takes in a string and trims the white spaces from it
        UnaryOperator<String> rmwhitespace = str -> str.trim();
        String os = "  NULL     ";
        String fs = rmwhitespace.apply(os);
        System.out.println("STRING UNARYOPERATOR: -" + fs + "-");
        
        
//		UnaryOperator takes in a list and adds "student" to each student
        UnaryOperator<List<String>> makestudent = lst -> lst.stream()
                .map(name -> "Student " + name)
                .collect(Collectors.toList()); 
        List<String> ol = List.of("Jenny", "Juan", "Jhonny", "Janice");
        List<String> nl = makestudent.apply(ol);
        System.out.println("LIST UNARYOPERATOR: " + nl);
            
            
//		UnaryOperator takes in a class object and adds a title to the name
        class Recruit {
            private String name;
            public Recruit(String name) {
                this.name = name;
            }
            public Recruit addTitle(String title) {
                this.name = title + " " + this.name;
                return this;
            }
            @Override
            public String toString() {
                return name;
            }
        }
        Recruit recruit = new Recruit("Frederick");
        UnaryOperator<Recruit> makeprof = p -> p.addTitle("Proffesor");
        Recruit prof = makeprof.apply(recruit);
        System.out.println("OBJECT UNARYOPERATOR: " + prof);

////////////////////////////////////////////////////////////////////////////////

        System.out.println();
        System.out.println("///// BINARYOPERATORS /////");

//		BinaryOperator takes in two integers and the output is the sum
        BinaryOperator<Integer> add = (a, b) -> a + b;
        Integer sum = add.apply(7, 4);
        System.out.println("INTEGER BINARYOPERATOR: the sum is " + sum);
        
        
//		BinaryOperator takes in two strings and concatenate them
        BinaryOperator<String> concat = (a, b) -> a + b;
        String f = concat.apply("Academia", "Java");
        System.out.println("STRING BINARYOPERATOR: " + f); 
        
        
//		BinaryOperator takes in two lists and merges them
        BinaryOperator<List<String>> juntar = (salon1, salon2) -> {
            List<String> juntarsalon = new ArrayList<>(salon1);
            juntarsalon.addAll(salon2);
            return juntarsalon;
        };
        List<String> salon1 = List.of("Mario", "Bowser");
        List<String> salon2 = List.of("Sonic", "Tails");
        List<String> salon3 = juntar.apply(salon1, salon2);
        System.out.println("LIST BINARYOPERATOR: " + salon3);
        
        
//		BinaryOperator takes 2 Novio Objects and combines them with a class method
        class Novio {
            private String nombre;
            private String apellido;
            public Novio(String nombre, String apellido) {
                this.nombre = nombre;
                this.apellido = apellido;
            }
            public Novio casar(Novio other) {
                return new Novio(this.nombre, other.apellido);
            }
            @Override
            public String toString() {
                return nombre + " " + apellido;
            	}
        	}
            Novio novia = new Novio("Alicia", "Perez");
            Novio novio = new Novio("Gilberto", "Guerrero");
            BinaryOperator<Novio> Casados = (nov1, nov2) -> nov1.casar(nov2);
            Novio novianew = Casados.apply(novia, novio);
            System.out.println("OBJECT BINARYOPERATOR: " + novianew);
	}

}
