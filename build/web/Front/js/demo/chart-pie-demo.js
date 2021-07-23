// Set new default font family and font color to mimic Bootstrap's default styling
Chart.defaults.global.defaultFontFamily = 'Nunito', '-apple-system,system-ui,BlinkMacSystemFont,"Segoe UI",Roboto,"Helvetica Neue",Arial,sans-serif';
Chart.defaults.global.defaultFontColor = '#858796';

// Pie Chart Example
$.get('tableServlet', function(array1){
        var obj = JSON.parse(array1);
        var nome = [];
        var db = [];
        for( var i=0; i<obj.length; i++){
            nome.push(obj[i].Nome);
            db.push(obj[i].Db);
      }
var ctx = document.getElementById("myPieChart");
var myPieChart = new Chart(ctx, {
  type: 'pie',
  data: {
    labels: nome,
    datasets: [{
      data: db,
      backgroundColor: ['#9e2b25', '#1cc88a', '#36b9cc'],
      hoverBackgroundColor: ['#9e2b25', '#17a673', '#2c9faf'],
      hoverBorderColor: "#9e2b25",
    }],
  },
  options: {
    maintainAspectRatio: false,
    tooltips: {
      backgroundColor: "rgb(255,255,255)",
      bodyFontColor: "#858796",
      borderColor: '#dddfeb',
      borderWidth: 1,
      xPadding: 15,
      yPadding: 15,
      displayColors: false,
      caretPadding: 10,
    },
    legend: {
      display: false
    },
    cutoutPercentage: 80,
  },
});
})