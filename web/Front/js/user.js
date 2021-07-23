window.onload = function() {

    
    get_nameGen();

function get_nameGen() {
    const name = document.getElementById("nameGen");
    
    let true_name = getCookieValue("username");
    
    name.innerHTML = true_name;
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

get_userData();

function get_userData() {
    $.get('getDataServlet', function(data){
             console.log(data)
             var obj = JSON.parse(data);
             var username = [];
             var name = [];
             var age = [];
             var email=[];
             
             for( var i=0; i<obj.length; i++){
                 username.push(obj[i].Username);
                 name.push(obj[i].Nome);
                 age.push(obj[i].Idade);
                 email.push(obj[i].Email);
             }
             
             const real_username = document.getElementById("trueUsername");
             const real_name = document.getElementById("trueName");
             const real_age = document.getElementById("trueAge");
             const real_email =document.getElementById("trueEmail");
             
         
        let true_username = "";
        let true_name = "";
        let true_age = "";
        let true_email = "";
        
        for( var i=0; i<obj.length; i++){
        
        true_name += '<div class="input-group-prepend"><span class="input-group-text" id="user-icon"><i class="fas fa-user-circle icon-login"></i></span></div>';
        true_name += '<input type="text" name="name" class="form-control form-control-user" id="InputName" placeholder="'+name[i]+'" required>';
        
        true_age += '<div class="input-group-prepend"><span class="input-group-text" id="password-icon"><i class="fas fa-calendar-alt icon-login"></i></span></div>'
        true_age += '<input type="number" name="age" class="form-control form-control-user" id="InputAge" placeholder="'+age[i]+'" required>';
        
        true_username += '<div class="input-group-prepend"><span class="input-group-text" id="user-icon"><i class="fas fa-user icon-login"></i></span></div>';
        true_username += '<input type="text" name="username" class="form-control form-control-user" id="InputUsername" placeholder="'+username[i]+'" required>';
        
        true_email += '<div class="input-group-prepend"><span class="input-group-text" id="user-icon"><i class="fas fa-envelope icon-login"></i></span></div>';
        true_email += '<input type="email" name="email" class="form-control form-control-user" id="InputEmail" placeholder="'+email[i]+'" required>';
        
        }
        
        real_username.innerHTML = true_username;
        real_name.innerHTML = true_name;
        real_age.innerHTML = true_age;
        real_email.innerHTML = true_email;
 
    });
}
    
}