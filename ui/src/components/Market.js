import React, { Component } from 'react';
import Outcome from './Outcome';

class Market extends Component {
    render() {
        const { name, outcome } = this.props;
        const outcomeList = renderOutcomes(outcome);
        return (<div className="market_section">
            <h3 className="section-head">{name}</h3>
            {outcomeList}
        </div>);
    }
}

const renderOutcomes = (outcomes) => {
  if (!outcomes || outcomes.length === 0) {
    return <p>Nothing yet. Come back soon.</p>;
  }
  return outcomes.map((outcome) => {
    return <Outcome key={outcome.outcomeId} {...outcome} />;
  });
}

export default Market;