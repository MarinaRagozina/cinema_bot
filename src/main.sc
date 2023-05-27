require: slotfilling/slotFilling.sc
  module = sys.zb-common
  
theme: /

    state: Start
        q!: $regex</start>
        a: Добро пожаловать в наш кинотеатр! Я - ваш персональный чатбот, готовый помочь вам с выбором фильма и билетов.

    state: NoMatch || noContext = true
        event!: noMatch
        a: Извините, я не понимаю ваш запрос. Пожалуйста, попробуйте перефразировать или задать другой вопрос.