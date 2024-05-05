
var options = {
  chart: {
    type: 'bar',
    height: 350
  },
   dataLabels: {
    enabled: false,
  },
  series: [{
    name: 'Account ',
  data:account
    
  }, {
    name: 'Competitor',
    data:competitor
  }],
  xaxis: {
    categories: ["Twitter","FaceBook", "Instagram"]
  }
};


var chart = new ApexCharts(document.querySelector("#graph3"), options);
chart.render();

  
   
  
    
    
    
function closed()
{
  const element=document.getElementsByClassName("side-nav")[0];
  console.log(element);
  const menu= document.getElementsByClassName("small-menu")[0];
  console.log(menu);
  element.style.display="none";
  

}

function show()
{

  const element=document.getElementsByClassName("side-nav")[0];
element.style.display="block";
 
  

  }
