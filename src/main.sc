require: functions.js
require: ticket.sc
require: patterns.sc
  module = sys.zb-common
  
init:
    bind("preProcess", function($context) {
        if (!$jsapi.context().session.lastState) $reactions.answer("Добро пожаловать в наш кинотеатр!"); 
    });
    
    bind("postProcess", function($context) {
        $jsapi.context().session.lastState = $.currentState;
        if ($jsapi.context().session.lastState !== "/StopSession") $reactions.timeout({interval: 180, targetState: "/StopSession"});
    });
    
theme: /

    state: Start
        q!: $regex</start>
        a: Я Ваш персональный чатбот, чем могу помочь?

    state: AnyQuestions
        a: Остались ли у Вас еще вопросы?
        q: $no : endSession || toState = "/StopSession"
        
        state: Yes
            q: $yes
            a: Какой у вас вопрос?
        
    state: StopSession
        if: $parseTree._Root !== "endSession"
            a: Увы, я не дождался Вашего ответа.
        a: Всегда рад помочь. До свидания!
        script: $jsapi.stopSession();
    
    state: NoMatch || noContext = true
        event!: noMatch
        a: Извините, я не понимаю Ваш запрос. Пожалуйста, попробуйте перефразировать или задать другой вопрос.
    
    state: SwitchToOperator
        q!: * $switchToOperator *
        a: Соединяю с оператором.
        script: switchToOperator();
        
    state: Reset
        a: Сброс сессионных данных
        script: $jsapi.stopSession();