<?php
   try {  
   require 'DB_Manager.php'; 
   
   $modelCode = $_REQUEST["modelCode"]; 
   $factoryName = $_REQUEST["factoryName"]; 
   $modelName = $_REQUEST["modelName"]; 
   $engineCapacity = $_REQUEST["engineCapacity"]; 
   $gearbox = $_REQUEST["gearbox"]; 
   $seats = $_REQUEST["seats"]; 

   $sql = "INSERT INTO `Model`(  `modelCode`,`factoryName`,`modelName`,`engineCapacity`,`gearbox`,`seats`)
   VALUES ( '$modelCode', '$factoryName', '$modelName', '$engineCapacity', '$gearbox', '$seats')";
   if ($conn->query($sql) === TRUE) {  
   $last_id = $conn->insert_id;  
   echo $last_id; 
   }  
   else 
   {   echo "Error: " . $sql . "\n" . $conn->error; 
 } 
 }  catch(Exception $e) { 
 echo "Exception Error See Log....";
 error_log($e->getMessage() , 0); 
 } 
 $conn->close();
 ?> 