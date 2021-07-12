function goTo(url){
    window.location.href = url;
}

function ajax(options){
    function _econdeParam(data){
        return Object.entries(data).map(function (entry){
            return encodeURI(entry[0] + '=' + entry[1]);
        }).join('&');
    }
}

const xhr = new XMLHttpRequest();
xhr.onload = function (){
    if(xhr.status >= 100 && xhr.status < 400){
        options.success(JSON.parse(xhr.responseText));
    }
    else{
        options.error(JSON.parse(xhr.responseText));
    }
};
xhr.open(options.method, options.url,true);
xhr.setRequestHeader('Accept','application/json','charset=UTF-8');
xhr.setRequestHeader('X-Requested-With','XMLHttpRequest');
if(options.method === 'POST'){
    xhr.setRequestHeader('Content-Type','application/x-www-form-urlencoded');
    xhr.send(_encodeParam(options.data));
}
else{
    xhr.send();
}

function dictionary(){
    return {
        'patterMismatch' : 'Formato non valido',
        'rangeOverflow' : 'Il valore è piu grande di %s',
        'rangeUnderflow' : 'Il valore è piu piccolo di %s',
        'tooLong' : 'Non deve superare la lunghezza di %s caratteri',
        'tooShort' : 'Non deve essere piu piccola di %s caratteri',
        'stepMismatch' : 'Deve avere uno step di %s',
        'valueMissing' : 'Campo obbligatorio',
    };
}

function validateForm(form){
    const dict = dictionary();

    function _reportError(event){
        const _el = event.target;
        const errors = [];
        if(_el.validity.tooShort){
            errors.push(dict['tooShort'].replace("%s",_el.getAttribute("minlenght")));
        }
        if(_el.validity.tooLong){
            errors.push(dict['tooLong'].replace("%s",_el.getAttribute("maxlenght")));
        }
        if(_el.validity.patternMismatch){
            errors.push(dict['patterMismatch']);
        }
        if(_el.validity.valueMissing){
            errors.push(dict['valueMissing']);
        }
        if(_el.validity.rangeUnderflow){
            errors.push(dict['rangeUnderflow'].replace("%s",_el.getAttribute("min")));
        }
        if(_el.validity.rangeOverflow){
            errors.push(dict['rangeOverflow'].replace("%s",_el.getAttribute("max")));
        }
        if(_el.validity.stepMismatch){
            errors.push(dict['stepMismatch'].replace("%s",_el.getAttribute("step")));
        }
        _el.parentElement.nextElementSibling.textContent = errors.join('|');
    }
}

function _reset(event){
    const _el = event.target;
    _el.parentElement.nextElementSibling.textContent ='';
}

form.setAttribute("novalidate","true");
const inputs = form.elements;
for(let i = 0; i < inputs.length; i++){
    let isValidInput = inputs[i].nodeName.match("INPUT|TEXTAREA|SELECT");
    if(inputs[i].willValidate !== 'undefined' && isValidInput){
        inputs[i].addEventListener('invalid',_reportError);
        inputs[i].addEventListener('focus',_reset);
    }
}

form.addEventListener('submit',function (event){
    if(form.checkValidity()){
        form.submit();
    }
    else{
        event.preventDefault()
    }
});



