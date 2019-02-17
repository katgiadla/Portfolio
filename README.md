[Go Bottom](#GoBottom)
<a name="GoTop"></a>

# Introduction 
In this repository are and will be kept every projects created by me from scratch. 


<br/>
<br/>
College Java repository:<br/>
https://github.com/Tchorson/Java_GCL07_lato_2018_Mateusz_Tchorek <br /> 


###### Todo 
Javascript <br /> 
Databases (MongoDb & Hibernate) <br /> 

<br/>

- Dictionary <br/>
	- ~~HTML popup window for connection ~~ <br>
	- A function / script enabling to create json with password and properties separately and update the database url: <br>
		-check json url at start <br>
		-set db url if not equals "",otherwise enforce user to put mongoDB cluster link<br>
		-set password in hidden file <br>
		-establish connection <br>
	- ~~-json file with db url and application properties~~  <br>
- Job offers collector <br/>
	- Add Jsoup to retrieve DOM  <br/>
	- Add functions to convert basic offers to POJOs  <br/>
	
<br>


## Projects in development:
<br/>

Job offers collector
======

#### Description
<ul>
	<li>The project includes MySQL, JavaMail, Spring, jsoup </li><br /> 
	<li>Currently in development. </li><br/> 
</ul>

### Requirements

<ul>
	<li>Local MySQL server</li>
</ul>

Dictionary <br>
Note: The project is just for my personal needs, I will improve it later
======

#### Description:

<ul>
	<li>Spring rest API </li>
	<li>MongoDB Atlas database </li>
	<li>Bson Data exchange </li>
</ul>

#### Requirements:
<ul>
	<li> Own MongoDB Atlas cluster </li>
	<li> Json file with admin's db password </li>
</ul>

#### Installation
<ul> 
	<li>Upload file named "data1.json" to the db_access folder according to the formula: <br/> </li>

```json
{
  "password": "yourPassword"
}
```

<li> In ConnectToDatabase class alter connectToDB() method return string to your own database url <br/></li>
</ul>

#### Usage
The page consists of 2 sections: <br/>
- Input area that consists of:<br/>
	- Add vocabulary service <br/>
	- Remove vocabulary service <br/>
- Vocabulary table
<br/>
Each service has 3 text inputs which should be fulfilled in the following order: 


| Word  | Translation | Language |
| ----- | ----------- | -------- |
| Buy   | Kaufen      | German   |

<br/>
If you feel you no longer need that word you can remove it by putting previous data in the same order in three text inputs below.  

<br/>

<br>
You can also include multiple translations like this: <br/> 

| Word  | Translation      | Language |
| ----- | ---------------- | -------- |
| Word  | Meaning1 <br> Meaning2	   | German   |


<br/>
This will divide the translation and add the same word with each diffirent meaning to the vocabulary table.<br/>

```diff
- Warning 
Deleting multi translation does not work yet. 
First word cannot be deleted for unknown reason.
Propably invisible character.
```


#### Restrictions
<a name="GoBottom"></a>
<br/>
In order to dispose of the 'unremoveable' follow the pattern: <br/>

| Word  | Translation | Language |
| ----- | ----------- | -------- |
| Buy   | Kaufen      | Delete   |

<br/>

```diff
- Warning
It deletes the first met word, may result in deleting the one with diffirent translation. Needs to be fixed. 
```

However, there is an alternative. Instead of "Delete" use "Deletemany".
This will result in deleting each specified word in the vocabulary collection.

<a name="GoBottom"></a>
[Go Top](#GoTop)
