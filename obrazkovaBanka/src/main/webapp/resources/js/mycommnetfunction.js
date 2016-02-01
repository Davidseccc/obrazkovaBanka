jQuery(document).ready(function(){
        jQuery('#myButton').on('click', function(event) {        
             jQuery('#comments').toggle('show');
        });
    });

jQuery(document).ready(function(){
    jQuery('#showpassbutton').on('click', function(event) {        
         jQuery('#changepassword').toggle('show');
    });
});