var allCard ;
var distribute ;
var ajax;
var month;
var dview;
var setting;

$().ready(function(){
    initiate();

});


function initiate(){

    allCard  = new ALLCARD();
    ajax = new AJAX();
    month = new MONTH();
    distribute = new DISTRIBUTE();
    dview = new DVIEW();
    setting = new SETTING();
}