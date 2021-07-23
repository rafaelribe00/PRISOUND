$('document').ready(function(){
    $.ajax({
        type: "POST",
        url: "/historyServlet",
        dataType: "json",
        success: function (data){
            
           // var nome = [];
           // var db = [];
            
            //for(var i=0; i<data.length(); i++){
               // nome.push(data[i].name);
                //db.push(data[i].db);
            //}
            
            //grafico(nome, db);
            
        }
    })
})

//function grafico(name, decibeis){
var ctx = document.getElementById('myBarChartda').getContext('2d');

var chart = new Chart(ctx, {
    // The type of chart we want to create
    type: 'bar',

    // The data for our dataset
    data: {
        labels: [Janeiro, Fevereiro, Março], //name, //quantidade de colunas
        datasets: [{
            label: 'Average Level of Decibels last day',
            backgroundColor: 'rgb(158, 43, 37)',
            borderColor: 'rgb(158, 43, 37)',
            data: [10, 20, 30]
        }]
    },

    // Configuration options go here
    options: {
        scales: {
            yAxes: [{
                ticks: {
                    beginAtZero: true
                }
            }],
        }
    }
});
//}