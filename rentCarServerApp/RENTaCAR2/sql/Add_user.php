<?php
   try {  
   require 'DB_Manager.php'; 
   
   $userName = $_REQUEST["userName"]; 
   $password = $_REQUEST["password"]; 
   $userEmail = $_REQUEST["userEmail"]; 
   $userId = $_REQUEST["userId"]; 
   $hint = $_REQUEST["hint"]; 
   

   $sql = "INSERT INTO `Model`(  `userName`,`password`,`userEmail`,`userId`,`hint`)
   VALUES ( '$userName', '$password', '$userEmail', '$userId', '$hint')";
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