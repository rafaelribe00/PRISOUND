window.onload = function() {

get_nameGen();

function get_nameGen() {
    const name = document.getElementById("nameGen");
    
    let true_name = getCookieValue("username");
    
    name.innerHTML = true_name;
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

    
    function dismissModal(){
        console.log("1");
        $.get('closeAlertServlet', function(){
            console.log("3");
            $("#alertSensorModal").modal("hide");

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

get_dataCards();


  function get_dataCards() {
      $.get('dashboardCardsServlet', function(array1){
             var obj = JSON.parse(array1);
             var alerta = [];
             var name = [];
             for( var i=0; i<obj.length; i++){
                 alerta.push(obj[i].Alerta);
                 name.push(obj[i].Nome);
           }
     
      const GoodCard = document.getElementById("GoodCard");
      const BadCard = document.getElementById("BadCard");
      const BrokenCard = document.getElementById("BrokenCard");
         
       let true_BadCard = 0;
       let true_GoodCard = 0;
       let true_BrokenCard = 0;
       let array_sensores = [];
          
      for( var i=0; i<obj.length; i++){
      
     
       if (array_sensores.includes(name[i])) {

       }
       else {
        array_sensores.push(name[i]);
        console.log(array_sensores);
        
      if ( alerta[i] == 0) {
          true_GoodCard += 1;
      }
      else if (alerta[i] == 1) {
          true_BadCard += 1;
      }
      else {
          true_BrokenCard += 1;
      }
      
     }
    }
    GoodCard.innerHTML = true_GoodCard;
    BadCard.innerHTML = true_BadCard;
    BrokenCard.innerHTML = true_BrokenCard;
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

}
