package com.bgl.exercise.gameoflife.rule;

import com.bgl.exercise.gameoflife.constant.CellLifeState;
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
public class RuleEngineTest {

    @Mock
    private StateTransitionRule rule1;
    @Mock
    private StateTransitionRule rule2;

    private RuleEngine ruleEngine;

    @BeforeEach
    public void setup() {
        ruleEngine = new RuleEngine(List.of(rule1, rule2));
    }

    @Test
    void shouldOnlyApplyRuleWhenConditionMatches() {
        when(rule1.matches(any(), anyInt())).thenReturn(false);
        when(rule2.matches(any(), anyInt())).thenReturn(true);

        ruleEngine.applyNextStateRule(CellLifeState.DEAD, 1);

        verify(rule1, times(0)).nextState();
        verify(rule2).nextState();
    }

    @Test
    void shouldApplyFirstRuleIfMultipleRuleConditionMatches() {
        when(rule1.matches(any(), anyInt())).thenReturn(true);

        ruleEngine.applyNextStateRule(CellLifeState.DEAD, 1);

        verify(rule1).nextState();
        verify(rule2, times(0)).nextState();
    }

    @Test
    void shouldNotApplyRuleIfNoneConditionMatches() {
        when(rule1.matches(any(), anyInt())).thenReturn(false);
        when(rule2.matches(any(), anyInt())).thenReturn(false);

        ruleEngine.applyNextStateRule(CellLifeState.DEAD, 1);

        verify(rule1, times(0)).nextState();
        verify(rule2, times(0)).nextState();
    }
}
