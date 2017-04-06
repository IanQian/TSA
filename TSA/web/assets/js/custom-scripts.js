/*------------------------------------------------------
    Author : www.webthemez.com
    License: Commons Attribution 3.0
    http://creativecommons.org/licenses/by/3.0/
---------------------------------------------------------  */

(function ($) {
    "use strict";

    var num1=41;
    var num2=20;
    
    var smt1=32.3;
    var lstm1=34.8;
    var mainApp = {

        initFunction: function () {
            /*MENU 
            ------------------------------------*/
            $('#main-menu').metisMenu();
			
            $(window).bind("load resize", function () {
                if ($(this).width() < 768) {
                    $('div.sidebar-collapse').addClass('collapse');
                } else {
                    $('div.sidebar-collapse').removeClass('collapse');
                }
            });

              

            /* MORRIS DONUT CHART 1
			----------------------------------------*/
            Morris.Donut({
                element: 'morris-donut-chart1',
                data: [{
                    label: "SYSTEM-1",
                    value: smt1.toString()
                }, {
                    label: "SYSTEM-2",
                    value: lstm1.toString()
                }],
				   colors: [
    '#A6A6A6','#8ceab9',
    '#8ceab9' 
  ],
                resize: true
            });


            /* MORRIS LINE CHART 2
			----------------------------------------*/
            
            Morris.Donut({
                element: 'morris-donut-chart2',
                data: [{
                    label: "SYSTEM-1",
                    value: num1.toString()
                }, {
                    label: "SYSTEM-2",
                    value: num2.toString()
                }],
				   colors: [
    '#A6A6A6','#8ceab9',
    '#8ceab9' 
  ],
                resize: true
            });
           
        

            $('.donut-chart').cssCharts({type:"donut"}).trigger('show-donut-chart');


  
       
	 
        },

        initialization: function () {
            mainApp.initFunction();

        }

    }
    // Initializing ///

    $(document).ready(function () {
		$(".dropdown-button").dropdown();
		$("#sideNav").click(function(){
			if($(this).hasClass('closed')){
				$('.navbar-side').animate({left: '0px'});
				$(this).removeClass('closed');
				$('#page-wrapper').animate({'margin-left' : '260px'});
				
			}
			else{
			    $(this).addClass('closed');
				$('.navbar-side').animate({left: '-260px'});
				$('#page-wrapper').animate({'margin-left' : '0px'}); 
			}
		});
		
        mainApp.initFunction(); 
    });

	$(".dropdown-button").dropdown();
	
}(jQuery));
