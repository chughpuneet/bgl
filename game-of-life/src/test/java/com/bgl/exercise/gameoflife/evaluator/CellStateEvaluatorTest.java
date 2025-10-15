package com.bgl.exercise.gameoflife.evaluator;

import com.bgl.exercise.gameoflife.constant.CellLifeState;
import com.bgl.exercise.gameoflife.rule.CellStateTransitionRule;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CellStateEvaluatorTest {

    @Mock
    private CellStateTransitionRule rule1;
    @Mock
    private CellStateTransitionRule rule2;

    private CellStateEvaluator cellStateEvaluator;

    @BeforeEach
    public void setup() {
        cellStateEvaluator = new CellStateEvaluator(List.of(rule1, rule2));
    }

    @Test
    void shouldOnlyApplyRuleWhenConditionMatches() {
        when(rule1.matches(any(), anyInt())).thenReturn(false);
        when(rule2.matches(any(), anyInt())).thenReturn(true);

        cellStateEvaluator.evaluate(CellLifeState.DEAD, 1);

        verify(rule1, times(0)).nextState();
        verify(rule2).nextState();
    }

    @Test
    void shouldApplyFirstRuleIfMultipleRuleConditionMatches() {
        when(rule1.matches(any(), anyInt())).thenReturn(true);

        cellStateEvaluator.evaluate(CellLifeState.DEAD, 1);

        verify(rule1).nextState();
        verify(rule2, times(0)).nextState();
    }

    @Test
    void shouldNotApplyRuleIfNoneConditionMatches() {
        when(rule1.matches(any(), anyInt())).thenReturn(false);
        when(rule2.matches(any(), anyInt())).thenReturn(false);

        cellStateEvaluator.evaluate(CellLifeState.DEAD, 1);

        verify(rule1, times(0)).nextState();
        verify(rule2, times(0)).nextState();
    }
}
