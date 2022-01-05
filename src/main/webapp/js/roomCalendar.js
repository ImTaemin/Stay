/**
 * 
 */

//오늘, 년, 월, 일, 1일, 말일
let now = new Date();
let year = now.getFullYear();
let month = now.getMonth();
let date = now.getDate();
let firstDate = new Date(year,month, 1);
let lastDate = new Date(year,month,0);


let s='';
for(firstDate; lastDate>=firstDate; firstDate++ ){
    
}