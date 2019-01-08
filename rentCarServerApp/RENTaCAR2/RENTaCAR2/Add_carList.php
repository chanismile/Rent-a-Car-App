<?php
   try {  
   require 'DB_Manager.php'; 
   
   $bb = $_REQUEST["bb"]; 
   $sql = "INSERT INTO `student_table`(  `bb`)
   VALUES ( '$bb')";
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