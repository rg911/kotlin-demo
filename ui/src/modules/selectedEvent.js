import axios from 'axios';
import { createSelector } from 'reselect';

export const GET_EVENT_SUCCESS = 'event/GET_EVENT_SUCCESS';
export const GET_EVENT_REQUESTED = 'event/GET_EVENT_REQUESTED';

const initialState = {
    isFetching: false,
    id: ''
};

export default (state = initialState, action) => {
    switch (action.type) {
    case GET_EVENT_SUCCESS:
        return  Object.assign(state, {
            id: action.event.eventId,
        });
    case GET_EVENT_REQUESTED:
        return Object.assign(state, {
            isFetching: true
        });
    default:
        return state;
    }
};

export const getEvent = (id) => {
    return (dispatch) => {
        dispatch({
            type: GET_EVENT_REQUESTED
        });

        axios.get(`http://localhost:8080/events/${id}`)
        .then((response) => {
            // eslint-disable-next-line no-console
            console.log('getEvent',response.data);
            dispatch({
                type: GET_EVENT_SUCCESS,
                event: response.data
            });
        });
    };
};

const eventsSelector = state => {
    return state.events.events;
};
const selectedEventSelector = state => {
    return state.selectedEvent.id;
};
export const eventSelector = createSelector(
    eventsSelector,
    selectedEventSelector,
    (items, id) => items[id]
);
export const getIsFetching = state => {
    return state.selectedEvent.isFetching;
};
