window.onload = function() {


get_nameGen();

function get_nameGen() {
    const name = document.getElementById("nameGen");
    
    let true_name = getCookieValue("username");
    
    name.innerHTML = true_name;
}
  
  get_cards();

  function get_cards() {
       
      $.get('CardlistServlet', function(array1){
          //alert(array1);
             var obj = JSON.parse(array1);
             var name = [];
             var alert = [];
             var local = [];
             for( var i=0; i<obj.length; i++){
                 alert.push(obj[i].Alerta);
                 name.push(obj[i].Nome);
                 local.push(obj[i].Local);
           }
        
          
     const card_content = document.getElementById("card_content");    
     
     let sensor_content = "";
     
     let array_sensores = [];
     
     alert.sort();
     alert.reverse();
        
     
      if (obj.length == "0") {
          sensor_content +="<div class='w-100'><div class='text-black-50 text-center'>No Sensors detected!</div></div>";
      } else {    
        
          for( var i=0; i<obj.length; i++){
             
             if (array_sensores.includes(name[i])) {

             }
             else {
             array_sensores.push(name[i]);
             
                  
          sensor_content += "<div class='column mt-4 ml-5 filterDiv "+ local[i]+"'><div class='flip-card' ontouchstart='this.classList.toggle(\'hover\');'><div class='flip-card-inner'> "
          
          
          
          if (alert[i] == "1") {
          sensor_content += "<div class='flip-card-front-danger'>"
          }
          else if (alert[i] == "2"){
          sensor_content += "<div class='flip-card-front-warning'>"
          }
          else {
          sensor_content += "<div class='flip-card-front'>"
          }
          
          
          
          sensor_content += "<div class='row justify-content-center'><div class='column py-3 mb-3'><h4 class='m-0 font-weight-bold text-center'>" + name[i] + "</h4></div>"
                
                
                
            if (alert[i] == "1") {
              sensor_content += "</div><div class='row justify-content-center'><div class='column text-center mb-3'><i class='fas fa-exclamation-triangle fa-6x'></i></div></div>"
            }
            else if (alert[i] == "2"){
              sensor_content += "</div><div class='row justify-content-center'><div class='column text-center mb-3'><i class='fas fa-unlink fa-6x'></i></div></div>"
            }
            else {
            sensor_content += "</div><div class='row justify-content-center'><div class='column text-center mb-3'><i class='fas fa-info-circle fa-6x'></i></div></div>"
            }
          
          
          
          sensor_content += "<div class='row justify-content-center'><div class='card-body text-center'>High Levels of Noise Detected</div></div>"
          
          
          
          if (alert[i] == "1") {
          sensor_content += "<div class='row justify-content-center'><div class='card-footer-danger mx-4 mt-2 mb-3 text-center'><p>3 hours ago</p></div></div></div>"
          sensor_content += "<div class='flip-card-back-danger'>"
          }
          else if (alert[i] == "2"){
          sensor_content += "<div class='row justify-content-center'><div class='card-footer-warning mx-4 mt-2 mb-3 text-center'><p>3 hours ago</p></div></div></div>"
          sensor_content += "<div class='flip-card-back-warning'>"
          }
          else {
          sensor_content += "<div class='row justify-content-center'><div class='card-footer-info mx-4 mt-2 mb-3 text-center'><p>3 hours ago</p></div></div></div>"
          sensor_content += "<div class='flip-card-back'>"
          }
          
          
          
          sensor_content += "<div class='row justify-content-center'><div class='column py-3 mb-3'><h4 class='m-0 font-weight-bold text-center'>Live Decibels</h4></div></div>"
          sensor_content += "<div class='row justify-content-center'><div class='column text-center mb-3'><i class='fas fa-volume-up fa-6x'></i></div></div>"
          sensor_content += "<div class='row justify-content-center'><div class='card-body text-center mt-3'>This sensor is detecting</div></div>"
          sensor_content += "<div class='row justify-content-center'>"
          
          
          if (alert[i] == "1") {
          sensor_content += "<div class='card-footer-danger mx-4 mt-n1 mb-3 text-center'><div id='" + name[i] +"'></div></div></div></div></div></div></div>"
          }
          else if (alert[i] == "2"){
          sensor_content += "<div class='card-footer-warning mx-4 mt-n1 mb-3 text-center'><div id='" + name[i] +"'></div></div></div></div></div></div></div>"
          }
          else {
          sensor_content += "<div class='card-footer-info mx-4 mt-n1 mb-3 text-center'><div id='" + name[i] +"'></div></div></div></div></div></div></div>"
          }
                 
          
          }
          
          }
          card_content.innerHTML = sensor_content;
          filterSelection("all");
        }     
    });
}
 
  get_data();


  function get_data() {
      $.get('sensorlistServlet', function(array1){
          //alert(array1);
             var obj = JSON.parse(array1);
             var name = [];
             var db = [];
             for( var i=0; i<obj.length; i++){
                 db.push(obj[i].Db);
                 name.push(obj[i].Nome);
           }
     
      console.log(name,db);
      for( var i=0; i<obj.length; i++){
      let teste = name[i];
      
      const sensordb = document.getElementById(teste);
      
    
       let true_sensordb = "";
     
        true_sensordb +="<h4>" + db[i] + " db </h4>";
      
      sensordb.innerHTML = true_sensordb;
    
    }
    });
  }
  
  
display_notification();



function display_notification() {
       
        $.get('notificationServlet', function(array1){
               var obj = JSON.parse(array1);
               var name = [];
               var alert = [];
               for( var i=0; i<obj.length; i++){
                   alert.push(obj[i].Alerta);
                   name.push(obj[i].Nome);
              }
           
          
        const small_notification = document.getElementById("small_notification");
            let little_notification = "";
          let array_notifications = [];
          

        if (obj.length == "0") {
            little_notification +="<div class='w-100'><div class='text-black-50 text-center'>No New Notifications!</div></div>";

        } else {    
           
            for( var i=0; i<obj.length; i++){

              if (array_notifications.includes(name[i])) {

              }
              else if (alert[i] == "0")    {
              
              }
              else {
                  array_notifications.push(name[i]);

              little_notification +="<a class='dropdown-item d-flex align-items-center' href='#'><div class='mr-3'>"
               
               if (alert[i] == "1") {
               little_notification +="<div class='icon-circle bg-danger'><i class='fas fa-exclamation-triangle text-white'></i></div></div><div><div class='small text-gray-500'>January 23, 2020</div><span class='font-weight-bold'>High Levels of Noise Detected in sensor "+ name[i]+".</span></div></a>"
              }
               else if (alert[i] == "2"){
               little_notification += "<div class='icon-circle bg-warning'><i class='fas fa-unlink text-white'></i></div></div><div><div class='small text-gray-500'>January 23, 2020</div><span class='font-weight-bold'>Possible Breakdown detected in sensor"+ name[i]+".</span></div></a>"
               }
               else {
               little_notification += "<div class='icon-circle bg-info'><i class='fas fa-info-circle text-white'></i></div></div><div><div class='small text-gray-500'>January 23, 2020</div><span class='font-weight-bold'>Sensor "+ name[i]+" as resume normal functioning.</span></div></a>"
               }
            }
          }
            small_notification.innerHTML = little_notification;
        }   
});
}

get_dataModal();


function get_dataModal() {
    let sensorr = "";
    $.get('alertServlet', function (array1){
    
        var obj = JSON.parse(array1);
        var nome = [];
        var alerta = [];
        for( var i=0; i<obj.length; i++){
            nome.push(obj[i].Nome);
            alerta.push(obj[i].Alerta);
        
        
        
        const sensor = document.getElementById("modalsensor");
        

        if (alerta[i] == "1" || alerta[i] == "2"){

            sensorr += nome[i];
            $("#alertSensorModal").modal({
                        backdrop: 'static',
                        keyboard: true, 
                        show: true
            });
        
            sensor.innerHTML = sensorr;
        }    
        }
    });
}


get_NotificationsBell();

function get_NotificationsBell() {
    $.get('notificationServlet', function(array1){
          //alert(array1);
             var obj = JSON.parse(array1);
             var alerta = [];
             var name = [];
             for( var i=0; i<obj.length; i++){
                 alerta.push(obj[i].Alerta);
                 name.push(obj[i].Nome);
           }
        
        const notificationsBell = document.getElementById("NotificationsBell");
        let true_notificationsBell = 0;
        let array_notifications = [];
        
        
        for( var i=0; i<obj.length; i++){
            
            if (array_notifications.includes(name[i])) {

            }
            else if (alerta[i] == "0")    {
              
            }
            else {
                  array_notifications.push(name[i]);
                  
                true_notificationsBell += 1;
            }
            
        }
        notificationsBell.innerHTML = true_notificationsBell;
    });
}

};