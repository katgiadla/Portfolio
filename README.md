# Introduction 
In this repository are and will be kept every projects created by me from scratch. 

[Top](#Top)
<a name="Bottom">Bottom</a>


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
</ul>
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
<br/>

| Word  | Translation | Language |
| ----- | ----------- | -------- |
| Buy   | Kaufen      | German   |

<br/>
<br/>
You can also include multiple translations like this: <br/> <br/>

| Word  | Translation      | Language |
| ----- | ---------------- | -------- |
| Word  | Meaning1/Meaning2| German   |

<br/>
This will divide the translation and add the same word with each diffirent meaning to the vocabulary table.

<br/>

```diff
- Warning
Incorrect translation formula will cause problems. 
```
<a name="restrictions">Restrictions</a>

<br/>
<br/>
If you feel you no longer need that certain word you can remove it by putting data in the same order in the row below but only for the one word-translation case!
<br/>
<br/>

#### Restrictions

[Restrictions](#restrictions)
<br/>
In order to get rid of the 'unremoveable' objects the text inputs should be filled in following pattern: <br/>
<br/>

| Word  | Translation | Language |
| ----- | ----------- | -------- |
| Buy   | Kaufen      | Delete   |

<br/>
<br/>
- "Delete" is a compulsory word, without this the undeletable word cannot be removed.
It will put force delete request that will remove inconvenient word. 

<a name="Top">Top</a>
[Bottom](#Bottom)
