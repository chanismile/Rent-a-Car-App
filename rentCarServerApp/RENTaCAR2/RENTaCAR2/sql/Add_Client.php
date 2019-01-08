<?php
   try {  
   require 'DB_Manager.php'; 
   
   $lName = $_REQUEST["lName"]; 
   $fName = $_REQUEST["fName"]; 
   $id = $_REQUEST["id"]; 
   $phoneNumber = $_REQUEST["phoneNumber"]; 
      $mail = $_REQUEST["mail"]; 
   $creditCard = $_REQUEST["creditCard"]; 

   $sql = "INSERT INTO `Client`(  `lName`,`fName`,`id`,`phoneNumber`,`mail`,`creditCard`)
   VALUES ( '$lName', '$fName', '$id', '$phoneNumber', '$mail', '$creditCard')";
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