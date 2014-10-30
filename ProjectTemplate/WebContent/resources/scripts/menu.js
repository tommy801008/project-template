var timeout = 200;
var closetimer = 0;
var menuItem = 0;

function menu_open() {
    menu_canceltimer();
    menu_close();
    menuItem = $(this).find('ul').css('visibility', 'visible');
}

function menu_close() {
    if (menuItem) {
        menuItem.css('visibility', 'hidden');
    }
}

function menu_timer() {
    closetimer = window.setTimeout(menu_close, timeout);
}

function menu_canceltimer() {
    if (closetimer) {
        window.clearTimeout(closetimer);
        closetimer = null;
    }
}

function menu_initialize(className) {
    $('#' + className + ' > li').bind('mouseover', menu_open);
    $('#' + className + ' > li').bind('mouseout', menu_timer);
}