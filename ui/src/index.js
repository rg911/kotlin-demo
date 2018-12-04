import React from 'react';
import { render } from 'react-dom';
import { Provider } from 'react-redux';
import { ConnectedRouter } from 'react-router-redux';
import { BrowserRouter as Router } from "react-router-dom"
import store, { history } from './store';
import App from './components/App';
import registerServiceWorker from './registerServiceWorker';

import './index.css';

const target = document.querySelector('#root')

render(
  <Provider store={store}>
    <Router >
      <div>
        <App />
      </div>
    </Router>
  </Provider>, target);
registerServiceWorker();
