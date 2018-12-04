import React, { Component } from 'react';
import EventListItem from './EventList'
import { bindActionCreators } from 'redux';
import { connect } from 'react-redux';
import { getEvents, eventsSelector, getIsFetching } from '../modules/events';

class Home extends Component {
  componentWillMount () {
    this.refresh = setInterval(() => {
      this.props.getEvents();
    }, 1000);
    this.props.getEvents();
  }
  componentWillUnmount(){
    clearInterval(this.refresh);
  }
  render() {
    const { events, isFetching, title } = this.props;

    console.log(this.props);
    return (
      <div className="page">
        <h2 className="banner">{title} </h2>
        {renderEventsList(events, isFetching)}
      </div>
    );
  }
}

const renderEventsList = (events, isFetching)  =>{

  if (!events || events.length === 0)
    return <p> Loading... </p>;

  const eventList = events.map((event) => {
    return <EventListItem key={event.eventId} {...event} />;
  });

  return (
    <table className="events_table">
      <thead>
        <tr>
          <th className="start" colSpan="2" scope="col">Start Time</th>
          <th scope="col">Category</th>
          <th scope="col">Sub Category</th>
          <th scope="col" colSpan="2">Event Name</th>
        </tr>
      </thead>
      <tbody>
        {eventList}
      </tbody>
    </table>
  );
};

const mapStateToProps = (state) => ({
  events: eventsSelector(state),
  isFetching: getIsFetching(state),
  title: 'Events'
});

const mapDispatchToProps = dispatch => bindActionCreators({
  getEvents: getEvents
}, dispatch);

export default connect(
mapStateToProps, 
mapDispatchToProps
)(Home);

