package com.example.test;

import com.example.test.repository.NumberRepository;
import com.example.test.service.NumberService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.NativeLabel;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.Result;
import com.vaadin.flow.data.binder.ValueContext;
import com.vaadin.flow.data.converter.Converter;
import com.vaadin.flow.router.Route;
import com.example.test.entity.Number;

@Route("main")
public class MainView extends VerticalLayout {

    private NumberService service;

    public MainView(NumberService service) {

        this.service = service;
        configureView();

    }

    private void configureView() {
        NumberField numberField = new NumberField();
        numberField.setLabel("Number");
        numberField.setMax(Long.MAX_VALUE);
        numberField.setAllowedCharPattern("([0-9])+");
        add(numberField);

        Binder<Number> binder = new Binder<>(Number.class);
        binder.forField(numberField).withConverter(new Converter<Double, Long>() {
            @Override
            public Result<Long> convertToModel(Double aDouble, ValueContext valueContext) {
                return Result.ok(aDouble.longValue());
            }

            @Override
            public Double convertToPresentation(Long aLong, ValueContext valueContext) {
                return aLong.doubleValue();
            }
        }).bind(Number::getNumber, Number::setNumber);
        binder.bindInstanceFields(this);

        numberField.addValueChangeListener(event -> {
            service.saveNumber(binder.getBean());
        });


        binder.setBean(new Number());

        Button button = new Button(new Icon(VaadinIcon.PLUS));
        button.addClickListener(event -> {
            numberField.setValue(numberField.getValue()+1);
            service.saveNumber(binder.getBean());
        });
        add(button);

    }
}
