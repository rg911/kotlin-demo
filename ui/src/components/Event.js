import React, { Component } from 'react';
import moment from 'moment';
import Market from './Market';
import { bindActionCreators } from 'redux';
import { connect } from 'react-redux';
import { getEvent, eventSelector, getIsFetching } from '../modules/selectedEvent';

class Event extends Component {
  constructor(props) {
    super(props);
    const { id } = this.props.match.params;
    this.state = { id };
  }
  componentWillMount() {
    this.refresh = setTimeout(() => {
      this.props.getEvent(this.state.id);
    }, 1000);
    this.props.getEvent(this.state.id);
  }
  componentWillUnmount(){
    clearInterval(this.refresh);
  }
  render() {
    const { event } = this.props;
    if (!event) {
        return <p> Loading... </p>;
    }
  
    const markets = renderMarkets(event.market)

    const { name, subCategory, startTime } = event;
    return (
      <div className="page">
        <div className="content-head">
          <h1>{formatName(name)}</h1>
          <h2 className="event-title sub-head">
              {subCategory} | {formatDate(startTime)} | {formatTime(startTime)}
          </h2>
          {markets}
      </div>
      </div>
    );
  }
};

const renderMarkets = (markets) => {
  if (!markets || markets.length.length === 0) {
    return <p>Nothing found yet...</p>;
  }
  return markets.map((market) => {
    return <Market key={market.marketId} {...market} />;
  });
}
const formatName = (name) => {
  return name.replace(/\|/g, '');
}
const formatDate = (time) => {
  return moment(time).format('dddd Do MMMM YYYY');
}
const formatTime = (time) => {
  return moment(time).format('h:mm a');
}

const mapStateToProps = (state) => {
  const event = eventSelector(state);
  const isFetching = getIsFetching(state);
  return {
      event,
      isFetching
  };
};

const mapDispatchToProps = dispatch => bindActionCreators({
  getEvent
}, dispatch);

export default connect(
mapStateToProps, 
mapDispatchToProps
)(Event);