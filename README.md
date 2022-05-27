Coverage: 80%

# IMS-ZakeAhmed

This is a project to create a bare-bones inventory managment application. Functionality inculudes ablility to create a customer,update a customer, read all customers and delete customers. The same can be one for items and orders. Can also update total cost of an order in real time with the addiction of items to said order. 

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.

### Prerequisites

What things you need to install the software and how to install them

```
Java - https://qa-community.co.uk/~/_/learning/java-beginner/java--installation
Ecilpise - https://qa-community.co.uk/~/_/learning/java-beginner/java--hello-world-example
mySQL - https://qa-community.co.uk/~/_/learning/databases-introduction/databases--installation
gitBash - https://qa-community.co.uk/~/_/learning/git/git--git-bash#installing-git-bash

```
Make sure to folk this repositry Zake-Ahmed/IMS-Starter.
Then you would need to edit the properties file in both main/resources and test/resources.
Open git bash inside the IMS-Starter repo that you forked and cloned into your local PC.
Then run the command java -jar ims-0.0.1-jar-with-dependencies.jar to start the application.

### How to use application
#### Create:
Follow the instructions outputed onto the console making note that a customer is needed to make an order.
#### Update:
Again follow the instructions outputed onto the console making note that items are needed in the database to add an item to the order. Also items are needed in the order to delete and item from the order.
#### Read;
The simplist method no object are needed in the database to run the read function on all three options.
#### Delete;
Make sure that the id is present in the database of the object you want to delete.


## Running the tests

Explain how to run the automated tests for this system. Break down into which tests and what they do

### Unit Tests 

Standard unit test on the DAO methods which is the back-bone of the application. It is where all the connection is happening between java and mySQL, inputing and updating the database.

```
@Test 
	public void testCreate() {
		final Item created = new Item(2L, "Car ", 1000.00D);
		assertEquals(created, DAO.create(created));
	}
```

### Integration Tests 
Mockito is used for integration testing, this is to mock the methods to test the controller is working for each object and each method called in the controller.

```

	@Mock
	private Utils utils;

	@Mock
	private ItemDAO dao;

	@InjectMocks
	private ItemController controller;
	
	@Test
	public void testCreate() {
		final String name = "watch";
		final double price = 10;
		final Item created = new Item(name, price);

		Mockito.when(utils.getString()).thenReturn(name);
		Mockito.when(utils.getDouble()).thenReturn(price);
		Mockito.when(dao.create(created)).thenReturn(created);

		assertEquals(created, controller.create());

		Mockito.verify(utils, Mockito.times(1)).getString();
		Mockito.verify(utils, Mockito.times(1)).getDouble();
		Mockito.verify(dao, Mockito.times(1)).create(created);
	}
```


## Deployment

Run maven clean package to make a new .jar file if any changes was made to the code.

## Built With

* [Maven](https://maven.apache.org/) - Dependency Management

## Versioning

We use [SemVer](http://semver.org/) for versioning.

## Authors

* **Chris Perrins** - *Initial work* - [christophperrins](https://github.com/christophperrins)
* **Zake Ahmed** - *Completed work* - [Zake-Ahmed](https://github.com/Zake-Ahmed)

## License

This project is licensed under the MIT license - see the [LICENSE.md](LICENSE.md) file for details 

*For help in [Choosing a license](https://choosealicense.com/)*

## Acknowledgments

* Hat tip to anyone whose code was used
* Inspiration
* etc
