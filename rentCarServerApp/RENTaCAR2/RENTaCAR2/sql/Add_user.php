<?php
   try {  
   require 'DB_Manager.php'; 
   
   $userName = $_REQUEST["userName"]; 
   $password = $_REQUEST["password"]; 
   $email = $_REQUEST["email"]; 
   $id = $_REQUEST["id"]; 
   $hint = $_REQUEST["hint"]; 
     $sql = "INSERT INTO `User`(  `userName`,`password`,`email`,`id`,`hint`)
   VALUES ( '$userName', '$password', '$email', '$id', '$hint')";
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