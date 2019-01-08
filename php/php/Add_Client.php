<?php
   try {  
   require 'DB_Manager.php'; 
   
   $lName = $_REQUEST["lName"]; 
   $fName = $_REQUEST["fName"]; 
   $clientId = $_REQUEST["clientId"]; 
   $phoneNumber = $_REQUEST["phoneNumber"]; 
   $clientEmail= $_REQUEST["clientEmail"]; 
   $creditCard = $_REQUEST["creditCard"]; 
   $clientPassword = $_REQUEST["clientPassword"]; 
   $sql = "INSERT INTO `Client`(  `lName`,`fName`,`clientId`,`phoneNumber`,`clientEmail`,`creditCard`,`clientPassword`)
   VALUES ( '$lName', '$fName', '$clientId', '$phoneNumber', '$clientEmail', '$creditCard', '$clientPassword')";
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