package org.ak.datagen.data;

import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

/**
 */
public class RandomUtil {

    public static Answer WITH_UPPER_BOUND_OF_RANDOM_RANGE = new Answer<Integer>() {

        @Override
        public Integer answer(InvocationOnMock invocationOnMock) throws Throwable {
            // Random.getInt(int bound) - the bound is *exclusive*
            return ((Integer) invocationOnMock.getArguments()[0]) - 1;
        }
    };

    public static Answer WITH_LOWER_BOUND_OF_RANDOM_RANGE = new Answer<Integer>() {

        @Override
        public Integer answer(InvocationOnMock invocationOnMock) throws Throwable {
            return 0;
        }
    };

    public static Answer WITH_LOWER_BOUND_OF_LONG_RANDOM_RANGE = new Answer<Long>() {

        @Override
        public Long answer(InvocationOnMock invocationOnMock) throws Throwable {
            return 0L;
        }
    };
}
