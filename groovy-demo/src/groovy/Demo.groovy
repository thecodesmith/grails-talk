package groovy

import groovy.transform.Canonical
import groovy.xml.MarkupBuilder

@Canonical
class Car {
    String make
    String model
    int year
}

Car car1 = new Car(make: "chevy", model: "impala", year: 2005)
def car2 = new Car("toyota", "corolla", 1999)
def car3 = new Car("mini", "cooper", 2008)

// Java collections
ArrayList<Car> cars = new ArrayList<>();
cars.add(car1);
cars.add(car2);
cars.add(car3);

for (int i = 0; i < cars.size(); i++) {
    Car car = cars.get(i);
    System.out.println("Car: " + car.getMake() + " " + car.getModel() + " " + car.getYear());
}

// Groovy collections
cars = [car1, car2, car3]

cars.each { car ->
    println "Car: ${car.make} ${car.model} ${car.year}"
}

cars.each { println it.make }

years = cars.collect { car ->
    car.year
}

int newest = years.max()

models = cars.collect { it.model } .sort()

cars.any()
cars.every()
cars.reverse()
cars.min()
cars.max()

if (cars.any { it.make == "honda" }) {
    throw new Exception()
}

// Java: Reading file contents to String
BufferedReader br = new BufferedReader(new FileReader("file.txt"));
try {
    StringBuilder sb = new StringBuilder();
    String line = br.readLine();

    while (line != null) {
        sb.append(line);
        sb.append(System.lineSeparator());
        line = br.readLine();
    }
    String text = sb.toString();
} finally {
    br.close();
}

// Groovy: Reading file contents to String
text = new File('file.txt').text

// Write lines to a file (no need to open/close streams)
File log = new File('test.log')

log.withWriter { out ->
    out.println "Header"
    out.println "line 1"
    out.println "line 2"
}

// XML MarkupBuilder
xml = new MarkupBuilder()

xml.cars {
    car {
        make 'toyota'
        model 'corolla'
        year 1999
    }
    car {
        make 'mini'
        model 'cooper'
        year 2008
    }
}

xml.toString()
/* XML Output:

<cars>
    <car>
        <make>toyota</make>
        <model>corolla</model>
        <year>1999</year>
    </car>
    <car>
        <make>mini</make>
        <model>cooper</model>
        <year>2008</year>
    </car>
</cars>

*/

xmlFile = new File('cars.xml')
xmlFile.withWriter { out ->
    out.write xml.toString()
}

// Or, even easier:
xmlFile.text = xml.toString()
