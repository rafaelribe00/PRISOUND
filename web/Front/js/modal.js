window.onload = function() {
    const sensor = document.getElementById("modalsensor");
    get_data();
    

    function get_data() {
        let sensorr = "";
        $.get('alertServlet', function (array1){

            var obj = JSON.parse(array1);
            var nome = [];
            var alerta = [];
            for( var i=0; i<obj.length; i++){
                nome.push(obj[i].Nome);
                alerta.push(obj[i].Alerta);
                
                if(alerta[i] == "1" || alerta[i] == "2"){
                    sensorr += nome[i];
                    console.log(sensorr);
                    $("#alertSensorModal").modal("show");
                }
                
                sensor.innerHTML = sensorr;
                
            }
        });
        
    }
    
   /*close();
    function close () {
        console.log("oi");
        $.get('closeAlertServlet', function(){
            $( "#dismiss" ).click(function() {
                $("#alertSensorModal").modal("hide");
            });
        });
    }*/
    
}
