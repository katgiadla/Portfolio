# Introduction 
In this repository are and will be kept every projects created by me from scratch. 


###### Todo 
Javascript <br /> 
Databases (MongoDb & Hibernate) <br /> 

<br/>
<br/>
College Java repository:<br/>
https://github.com/Tchorson/Java_GCL07_lato_2018_Mateusz_Tchorek <br /> 


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
</ul

<br/>
<br/>

Dictionary 
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
<br/>

#### Usage
The page consists of 2 sections: <br/>
- Input area that consists of:<br/>
	- Add vocabulary service <br/>
	- Remove vocabulary service <br/>
- Vocabulary table
<br/>
<br/>
Each service has 3 text inputs which should be fulfilled in the following order: <br/>

| Word  | Translation | Language |
| ----- | ----------- | -------- |
| Buy   | Kaufen      | German   |

If you feel you no longer need that certain word you can remove it by putting data in the same order in the row below.
<br/>
<br/>


#### Restrictions
Adding multiple translations may cause the words in database are unable to delete in a simply way. <br/>
In that text inputs should be filled in following pattern: <br/>

| Word  | Translation | Language |
| ----- | ----------- | -------- |
| Buy   | Kaufen      | Delete   |

- "Delete" is a compulsory word, without this the undeletable word cannot be removed.
It will put force delete request that will remove inconvenient word.
