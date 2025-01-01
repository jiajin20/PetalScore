/*
 * @Author: error: error: git config user.name & please set dead value or install git && error: git config user.email & please set dead value or install git & please set dead value or install git
 * @Date: 2024-12-05 11:44:44
 * @LastEditors: error: error: git config user.name & please set dead value or install git && error: git config user.email & please set dead value or install git & please set dead value or install git
 * @LastEditTime: 2024-12-20 11:11:39
 * @FilePath: \web-front\static\js\app.js
 * @Description: 这是默认设置,请设置`customMade`, 打开koroFileHeader查看配置 进行设置: https://github.com/OBKoro1/koro1FileHeader/wiki/%E9%85%8D%E7%BD%AE
 */


const linkColor = document.querySelectorAll('.nav__link')
function colorLink(){
  linkColor.forEach(l => l.classList.remove('active-link'))
  this.classList.add('active-link')
}
linkColor.forEach(l => l.addEventListener('click', colorLink))

/* <------------ SHOW HIDDEN MENU ----------> */

const showMenu = (toggleId, navbarId) =>{
  const toggle = document.getElementById(toggleId),
  navbar = document.getElementById(navbarId)
  if(toggle && navbar){
      toggle.addEventListener('click', ()=>{

          /* Show menu */
          navbar.classList.toggle('show-menu')
          
          /* Rotate toggle icon */
          toggle.classList.toggle('rotate-icon')
      })
  }
}
showMenu('nav-toggle','nav')


function checkUserSession_my() {
  axios.get('/vscf/getSessionUser') 
      .then(response => {
          if (response.data.success && response.data.user) {
              window.location.href = '/vscf/my.html';
          } else {
              window.location.href = '/vscf/login.html';
          }
      })
      .catch(error => {
          console.error('Error fetching user session:', error);
          window.location.href = '/vscf/login.html'; 
      });
}


function checkUserSession_category() {
  axios.get('/vscf/getSessionUser')
      .then(response => {
          if (response.data.success && response.data.user) {
              window.location.href = '/vscf/category.html';
          } else {
              window.location.href = '/vscf/login.html';
          }
      })
      .catch(error => {
          console.error('Error fetching user session:', error);
          window.location.href = '/vscf/login.html';
      });
}

function clearJSessionId() {
  // 设置过期日期为过去的时间来清除 cookie
  document.cookie = 'JSESSIONID=; expires=Thu, 01 Jan 1970 00:00:00 UTC; path=/';
}

