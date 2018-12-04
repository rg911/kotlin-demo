import axios from 'axios';
import _ from 'lodash';

export const GET_EVENTS_REQUESTED = 'event/GET_EVENTS_REQUESTED';
export const GET_EVENTS_SUCCESS = 'event/GET_EVENTS_SUCCESS';
export const GET_EVENT_SUCCESS = 'event/GET_EVENT_SUCCESS';

const initialState = {
    events: [],
    isFetching: false
};

export default (state = initialState, action) => {
    switch (action.type) {
    case GET_EVENTS_SUCCESS: {
        const newState =Object.assign({}, state);
        newState.events = _.keyBy(action.events, 'eventId');
        newState.isFetching = false;
        return newState;
    }   
    case GET_EVENTS_REQUESTED: {
        const newState = Object.assign({}, state);
        newState.isFetching = true;
        return newState;
    }
    case GET_EVENT_SUCCESS: {
        const newState = Object.assign({}, state);
        newState.events[action.event.eventId] = action.event;
        return newState;
    }
    default:
        return state;
    }
};

export const getEvents = () => {
    return (dispatch) => {
        dispatch({
            type: GET_EVENTS_REQUESTED
        });

        axios.get('http://localhost:8080/events')
        .then((response) => {
            // eslint-disable-next-line no-console
            dispatch({
                type: GET_EVENTS_SUCCESS,
                events: response.data
            });
        });
    };
};

export const eventsSelector = state => {
    return Object.values(state.events.events);
};
export const getIsFetching = state => {
    return state.selectedEvent.isFetching;
};