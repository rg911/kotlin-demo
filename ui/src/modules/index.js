import { combineReducers } from 'redux';
import { routerReducer } from 'react-router-redux';
import events from './events';
import selectedEvent from './selectedEvent';

export default combineReducers({
    routing: routerReducer,
    events,
    selectedEvent
});