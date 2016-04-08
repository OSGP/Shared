package com.alliander.osgp.shared.exceptionhandling;

public abstract class CorrelationUidException extends FunctionalException {

    private static final long serialVersionUID = 6419833632655339760L;

    public CorrelationUidException(final FunctionalExceptionType exceptionType, final ComponentType componentType,
            final Throwable cause) {
        super(exceptionType, componentType, cause);
    }

    public CorrelationUidException(final FunctionalExceptionType exceptionType, final ComponentType componentType) {
        super(exceptionType, componentType);
    }

}
