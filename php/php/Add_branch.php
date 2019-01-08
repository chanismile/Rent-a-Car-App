<?php
   try {  
   require 'DB_Manager.php'; 
   
   $address = $_REQUEST["address"]; 
   $numOfParking = $_REQUEST["numOfParking"]; 
   $sql = "INSERT INTO `Branch`(  `address`, `numOfParking`)
   VALUES ( '$address', '$numOfParking')";
   if ($conn->query($sql) === TRUE) {  
   $last_id = $conn->insert_id;  
   echo $last_id; 
   }  
   else 
   {   echo "Error: " . $sql . "\n" . $conn->error; 
 } 
 }  catch(Exception $e) { 
 echo "Exception Error See Log....";
 error_log($e->getMessage() , 78); 
 } 
 $conn->close();
 ?> 