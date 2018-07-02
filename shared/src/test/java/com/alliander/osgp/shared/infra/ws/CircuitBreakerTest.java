/**
 * Copyright 2018 Smart Society Services B.V.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 */
package com.alliander.osgp.shared.infra.ws;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CircuitBreakerTest {

    private CircuitBreaker circuitBreaker;

    @Before
    public void before() {
        this.circuitBreaker = new CircuitBreaker.Builder().withThreshold((short) 2).withInitialDuration(30)
                .withMaximumDuration(120).withMultiplier((short) 3).build();
    }

    @Test
    public void testInitiallyClosed() {
        Assert.assertTrue("Initial status should be CLOSED", this.circuitBreaker.isClosed());
    }

    @Test
    public void testOpenCircuit() {
        this.circuitBreaker.openCircuit();
        Assert.assertFalse("Should be OPEN after explicit open circuit request", this.circuitBreaker.isClosed());
    }

    @Test
    public void testCloseCircuit() {
        this.markTwoFailures();
        this.circuitBreaker.closeCircuit();
        Assert.assertTrue("Should be CLOSED after explicit close circuit request", this.circuitBreaker.isClosed());
    }

    @Test
    public void testClosedAfter1Failure() {
        this.markFailure();
        Assert.assertTrue("Status should be CLOSED after 1 failure", this.circuitBreaker.isClosed());
    }

    @Test
    public void testOpenAfter2Failures() {
        // The threshold is 2, so two consecutive failures open the
        // circuit breaker.
        this.markTwoFailures();

        Assert.assertFalse("Status should be OPEN after 2 failures", this.circuitBreaker.isClosed());
    }

    @Test
    public void testClosedAfter2FailuresAndWait() {
        // Trigger the circuit breaker to open
        this.markTwoFailures();
        // Wait until the circuit breaker is closed
        this.wait(35);

        Assert.assertTrue("Status should be CLOSED after waiting for 35 ms", this.circuitBreaker.isClosed());
    }

    @Test
    public void testDurationIncrease() {
        // Trigger the circuit breaker to open
        this.markTwoFailures();
        // Wait until the circuit breaker is closed
        this.wait(35);

        // We are in a half open state now. Another failure results
        // in tripling the duration from 30 to 90 milliseconds.
        this.markFailure();
        // Wait longer then the initial duration, but shorter than
        // multiplier * initial duration.
        this.wait(80);

        Assert.assertFalse("Status should be OPEN after waiting for 80 ms", this.circuitBreaker.isClosed());
    }

    @Test
    public void testMaximumDuration() {
        // Trigger the circuit breaker to open
        this.markTwoFailures();
        // Wait until the circuit breaker is closed
        this.wait(35);

        // We are in a half open state now. Another failure results
        // in tripling the timeout from 30 to 90 milliseconds.
        this.markFailure();
        // Wait until the circuit breaker is closed
        this.wait(95);

        // We are in a half open state now. Another failure results
        // in increasing the duration from 90 to its maximum of 120
        // milliseconds.
        this.markFailure();

        // Wait until the circuit breaker is closed
        this.wait(125);

        Assert.assertTrue("Status should be CLOSED after waiting for 125 ms", this.circuitBreaker.isClosed());
    }

    @Test
    public void testClosedAfterFailingOnceAfterSuccess() {
        this.markFailure();
        // One success resets the threshold to 2.
        this.markSuccess();
        this.markFailure();

        Assert.assertTrue("Status should be CLOSED, when there's one failure after a success",
                this.circuitBreaker.isClosed());
    }

    private void markFailure() {
        this.circuitBreaker.markFailure();
    }

    private void markTwoFailures() {
        this.markFailure();
        this.markFailure();
    }

    private void markSuccess() {
        this.circuitBreaker.markSuccess();
    }

    private void wait(final int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (final InterruptedException e) {
            Assert.fail("Sleep interrupted");
        }
    }
}
