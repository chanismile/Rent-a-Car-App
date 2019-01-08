<?php
   try {  
   require 'DB_Manager.php'; 
   
   $carBranchNo = $_REQUEST["carBranchNo"]; 
   $model = $_REQUEST["model"]; 
   $kilometer = $_REQUEST["kilometer"]; 
   $carId = $_REQUEST["carId"]; 
   $sql = "INSERT INTO `Cars`(  `carBranchNo`,`model`,`kilometer`,`carId`)
   VALUES ( '$carBranchNo', '$model', '$kilometer', '$carId')";
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