
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

// this is for the correction purpose


  
// BAR CHART
const barChartOptions = {
  series: [
    {
      data: array,
    },
  ],
  chart: {
    type: 'bar',
    height: 350,
    toolbar: {
      show: false,
    },
  },
  colors: ['#246dec', '#cc3c43', '#367952', '#f5b74f', '#4f35a1'],
  plotOptions: {
    bar: {
      distributed: true,
      borderRadius: 4,
      horizontal: false,
      columnWidth: '40%',
    },
  },
  dataLabels: {
    enabled: false,
  },
  legend: {
    show: false,
  },
  xaxis: {
    categories: ['Facebook','Twitter', 'Instagram'],
  },
  yaxis: {
    title: {
      text: 'Count',
    },
  },
};

const barChart = new ApexCharts(
  document.querySelector('#graph1'),
  barChartOptions
);
barChart.render();





 
const areaChartOptions = {
  series: [
    {
      name: 'Instagram followers',
      data:instagramList,
    },
    {
      name: 'Twitter follower',
      data:twitterList ,
    },
    {
		name:'Facebook follower',
		data:faceBookList
	}
  ],
  chart: {
    height: 350,
    type: 'area',
    toolbar: {
      show: true, 
    },
  },
  colors: ['#4f35a1', '#246dec','pink'],
  dataLabels: {
    enabled: false,
  },
  stroke: {
    curve: 'smooth',
  },
  labels: ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul'],
  markers: {
    size: 0,
  },
  yaxis: [
    {
      title: {
        text: 'follower count',
      },
    },
    {
      opposite: true,
      title: {
        text: 'follower growth  over time',
      },
    },
  ],
  tooltip: {
    shared: true,
    intersect: false,
  },
};

const areaChart = new ApexCharts(
  document.querySelector('#graph2'),
 areaChartOptions
);
areaChart.render();



//printing

